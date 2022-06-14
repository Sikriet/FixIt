package com.example.fixit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class valoracionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvnombre, tvcomentario, tv_valoracionPromedio;
    private TextView tv_uno, tv_dos, tv_tres, tv_cuatro, tv_cinco;
    private ProgressBar pb_1, pb_2, pb_3, pb_4, pb_5;
    private RatingBar ratingBar;
    String url, idPyme, nombrePyme;
    RequestQueue request;
    MyTag selected_pyme = new MyTag();
    List<Valoraciones> valoracionesList = new ArrayList<Valoraciones>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion);
        selected_pyme = (MyTag) getIntent().getSerializableExtra("selected_pyme");
        idPyme = selected_pyme.id_pyme;
        pb_5 = findViewById(R.id.pb_5);
        pb_4 = findViewById(R.id.pb_4);
        pb_3 = findViewById(R.id.pb_3);
        pb_2 = findViewById(R.id.pb_2);
        pb_1 = findViewById(R.id.pb_1);

        tv_uno = findViewById(R.id.tv_uno);
        tv_dos = findViewById(R.id.tv_dos);
        tv_tres = findViewById(R.id.tv_tres);
        tv_cuatro = findViewById(R.id.tv_cuatro);
        tv_cinco = findViewById(R.id.tv_cinco);

        tv_valoracionPromedio = findViewById(R.id.tv_valoracion);

        tvnombre = findViewById(R.id.tv_nombreVal);
        ratingBar = findViewById(R.id.ratingBar);
        tvcomentario = findViewById(R.id.tv_comentarioVal);

        recyclerView = (RecyclerView) findViewById(R.id.rv_Valoracion);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        request = Volley.newRequestQueue(getApplicationContext());
        cargarwebService();
    }

    private void cargarwebService() {
        url = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/valoracion/valoracionPyme.php?id_pyme=" + idPyme;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length() > 0) {

                        int[] valoracionList = new int[jsonArray.length()];
                        int cantUnaEstrella = 0;
                        int cantDosEstrellas = 0;
                        int cantTresEstrellas = 0;
                        int cantCuatroEstrellas = 0;
                        int cantCincoEstrellas = 0;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Valoraciones valoraciones = new Valoraciones();
                            valoraciones.setId_valoracion(jsonObject.getString("id_valoracion"));
                            valoraciones.setCalificacion_valoracion(jsonObject.getString("calificacion_valoracion"));
                            valoraciones.setId_pyme(jsonObject.getString("id_pyme"));
                            valoraciones.setRut_usuario(jsonObject.getString("rut_usuario"));
                            valoraciones.setComentario_valoracion(jsonObject.getString("comentario_valoracion"));
                            valoraciones.setNombre_pyme(jsonObject.getString("nombre_pyme"));
                            valoraciones.setNombre_usuario(jsonObject.getString("nombre_usuario"));
                            valoraciones.setApellido_usuario(jsonObject.getString("apellido_usuario"));
                            valoracionesList.add(valoraciones);
                            mAdapter = new ValoracionAdapter(valoracionesList, valoracionActivity.this);
                            recyclerView.setAdapter(mAdapter);

                            int valoracion = Integer.parseInt(valoracionesList.get(i).getCalificacion_valoracion());
                            boolean asd = valoracion == 1;
                            Toast.makeText(valoracionActivity.this, "" + asd, Toast.LENGTH_SHORT).show();
                            valoracionList[i] = valoracion;

                            if (valoracion == 1) {
                                cantUnaEstrella = cantUnaEstrella + 1;
                            }
                            if (valoracion == 2) {
                                cantDosEstrellas = cantDosEstrellas + 1;
                            }
                            if (valoracion == 3) {
                                cantTresEstrellas = cantTresEstrellas + 1;
                            }
                            if (valoracion == 4) {
                                cantCuatroEstrellas = cantCuatroEstrellas + 1;
                            }
                            if (valoracion == 5) {
                                cantCincoEstrellas = cantCincoEstrellas + 1;
                            }
                        }
                        float average = average(valoracionList);

                        tv_valoracionPromedio.setText("Estrellas Promedio de " + valoracionesList.get(0).getNombre_pyme() +": " + average);

                        ArrayList<Integer> cantEstrellas = new ArrayList<>();
                        cantEstrellas.add(cantUnaEstrella);
                        cantEstrellas.add(cantDosEstrellas);
                        cantEstrellas.add(cantTresEstrellas);
                        cantEstrellas.add(cantCuatroEstrellas);
                        cantEstrellas.add(cantCincoEstrellas);
                        int numMax = getMax(cantEstrellas);
                        pb_1.setMax(numMax);
                        pb_2.setMax(numMax);
                        pb_3.setMax(numMax);
                        pb_4.setMax(numMax);
                        pb_5.setMax(numMax);

                        pb_1.setProgress(cantUnaEstrella);
                        pb_2.setProgress(cantDosEstrellas);
                        pb_3.setProgress(cantTresEstrellas);
                        pb_4.setProgress(cantCuatroEstrellas);
                        pb_5.setProgress(cantCincoEstrellas);

                        tv_uno.setText("("+cantUnaEstrella+")");
                        tv_dos.setText("("+cantDosEstrellas+")");
                        tv_tres.setText("("+cantTresEstrellas+")");
                        tv_cuatro.setText("("+cantCuatroEstrellas+")");
                        tv_cinco.setText("("+cantCincoEstrellas+")");
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onResponse CATCH: " + e.toString());
                    Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Volley error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(stringRequest);
    }

    public static float average(int[] input) {
        float sum = 0f;
        for (int number : input) {
            sum = sum + number;
        }
        return sum / input.length;
    }

    public static int getMax(ArrayList<Integer> arr) {
        //Initialize max with first element of array.
        int max = arr.get(0);
        //Loop through the array
        for (int i = 0; i < arr.size(); i++) {
            //Compare elements of array with max
            if(arr.get(i) > max)
                max = arr.get(i);
        }

        return max;
    }
}
package com.example.fixit;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class valoracionActivity extends AppCompatActivity {

    private TextView tvnombre, tvcomentario, tvnombrep;
    private ProgressBar pb1, pb2, pb3, pb4, pb5;
    private RatingBar rb;
    String url, idPyme, nombrePyme;
    RequestQueue request;
    JSONObject jsonObject;
    JSONArray jsonArray;
    MyTag selected_pyme = new MyTag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion);
        selected_pyme = (MyTag) getIntent().getSerializableExtra("selected_pyme");
        idPyme = selected_pyme.id_pyme;
        pb1 = findViewById(R.id.pb);
        pb2 = findViewById(R.id.pb2);
        pb3 = findViewById(R.id.pb3);
        pb4 = findViewById(R.id.pb4);
        pb5 = findViewById(R.id.pb5);

        tvnombrep = findViewById(R.id.tv_valoracion);
        tvnombre = findViewById(R.id.tv_nombreVal);
        tvcomentario = findViewById(R.id.tv_nombreVal4);
        rb = findViewById(R.id.ratingBar);
        request = Volley.newRequestQueue(getApplicationContext());
        cargarwebService();
    }

    private void cargarwebService() {
        url = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/valoracion/valoracionPyme.php?id_pyme=" + idPyme;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    String tempVal = jsonArray.getString(0);
                    JSONObject jsonObject = new JSONObject(tempVal); //[{"contrasena_usuario":"1234"}]

                    String nombreP = jsonObject.getString("nombre_pyme");
                    float val = Float.parseFloat(jsonObject.getString("calificacion_valoracion"));
                    String nombreU = jsonObject.getString("nombre_usuario");
                    String apellidoU = jsonObject.getString("apellido_usuario");
                    String comentario = jsonObject.getString("comentario_valoracion");
                    String nombre_completo = nombreU + " " + apellidoU;
                    tvnombre.setText(nombre_completo);
                    tvcomentario.setText(comentario);
                    tvnombrep.setText(nombreP);
                    rb.setRating(val);
                    if (val == 4) {
                        pb3.setMax(1);
                        pb3.setProgress(1);
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
}
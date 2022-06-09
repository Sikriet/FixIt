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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion);
        idPyme = "1";
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
        url = "https://supernaturalism-sho.000webhostapp.com/api/solicitudes/valoracion/valoracionPyme.php?id_pyme=" + idPyme;
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
                    tvnombre.setText((nombreU) + " " + (apellidoU));
                    tvcomentario.setText(comentario);
                    tvnombrep.setText(nombreP);
                    rb.setRating(val);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Usuario no existente" + e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error maldito = " + e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "VOlley error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(stringRequest);
    }
}
package com.example.fixit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profileActivity extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_apellido;
    private EditText et_rut;
    private EditText et_correo;
    private String URL;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_rut = findViewById(R.id.et_apellido);
        et_correo = findViewById(R.id.et_email);

        getUserInfo();
    }

    public void getUserInfo() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        URL = "https://supernaturalism-sho.000webhostapp.com/api/solicitudes/usuario/seleccionar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    UserClass userClass = new UserClass();
                    userClass.setRut_usuario(jsonObject.getString("rut_usuario"));
                    userClass.setNombre_usuario(jsonObject.getString("nombre_usuario"));
                    userClass.setApellido_usuario(jsonObject.getString("apellido_usuario"));
                    userClass.setEmail_usuario(jsonObject.getString("email_usuario"));
                    userClass.setTelefono_usuario(jsonObject.getString("telefono_usuario"));
                    userClass.setDireccion_usuario(jsonObject.getString("direccion_usuario"));
                    userClass.setContrasena_usuario(jsonObject.getString("contrasena_usuario"));
                    userClass.setVehiculo_usuario(jsonObject.getString("vehiculo_usuario"));
                    userClass.setFecha_creacion(jsonObject.getString("fecha_creacion"));
                    userClass.setEstado_usuario(jsonObject.getString("estado_usuario"));

                    Log.d(TAG, "onResponse: " + userClass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
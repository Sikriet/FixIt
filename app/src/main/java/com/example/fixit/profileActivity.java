package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class profileActivity extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_apellido;
    private EditText et_telefono;
    private EditText et_correo;
    private String rut_logeado;
    UserClass userClass = new UserClass();
    private Button btn_update;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_telefono = findViewById(R.id.et_telefono);
        et_correo = findViewById(R.id.et_email);
        rut_logeado = getIntent().getExtras().getString("rut");
        btn_update = findViewById(R.id.btn_update);
        spinner = (Spinner) findViewById(R.id.spinner_editVehiculo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos_vehiculos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getUserInfo();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserInfo();
                Intent i = new Intent(getApplicationContext(), menuActivity.class);
                i.putExtra("rut", rut_logeado);
                startActivity(i);
            }
        });
    }

    public void getUserInfo() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/usuario/seleccionar.php?rut_usuario=" + rut_logeado;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
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

                    et_nombre.setText(userClass.getNombre_usuario());
                    et_apellido.setText(userClass.getApellido_usuario());
                    et_telefono.setText(userClass.getTelefono_usuario());
                    et_correo.setText(userClass.getEmail_usuario());
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

    public void editUserInfo() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/usuario/editar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(profileActivity.this, "Perfil editado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("rut_usuario", rut_logeado);
                params.put("nombre_usuario", et_nombre.getText().toString());
                params.put("apellido_usuario", et_apellido.getText().toString());
                params.put("email_usuario", et_correo.getText().toString());
                params.put("telefono_usuario", userClass.getTelefono_usuario());
                params.put("direccion_usuario", userClass.getDireccion_usuario());
                params.put("contrasena_usuario", userClass.getContrasena_usuario());
                params.put("vehiculo_usuario", spinner.getSelectedItem().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
package com.example.fixit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import java.util.ArrayList;


public class loginActivity extends AppCompatActivity{
    private EditText et_user, et_pass;
    private TextView lblerror;
    String url, user, pass;
    RequestQueue request;
    JSONObject jsonObject;
    Usuarios usu;
    private ArrayList<Usuarios>  listaU = new ArrayList<>();
    private String keyUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user = findViewById(R.id.et_user_login);
        et_pass=findViewById(R.id.et_pass_login);
        lblerror=findViewById(R.id.lbl_error);




    }

    public void Ingresar(View view){
        try {
            String verif;
            user = et_user.getText().toString();
            pass = et_pass.getText().toString();

            request = Volley.newRequestQueue(getApplicationContext());
            cargarwebService();
        }catch (Exception e){
            lblerror.setText(e.getMessage().toString());
            }

    }

    private void cargarwebService() {
            request = Volley.newRequestQueue(getApplicationContext());
            url = "https://supernaturalism-sho.000webhostapp.com/api/solicitudes/usuario/login.php?rut_usuario="+user;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        String tempVerif = jsonArray.getString(0);
                        JSONObject jsonObject = new JSONObject(tempVerif);
                        String verif = jsonObject.getString("contrasena_usuario");
                        if (verif.equals(pass)) {
                            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), menuActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Verifique su contraseña", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Usuario no existente", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }); request.add(stringRequest);
    }

    public void Registrar(View view){
      Intent i = new Intent(this, registerActivity.class);
      startActivity(i);
    }
}
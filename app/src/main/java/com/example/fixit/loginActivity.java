package com.example.fixit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user = findViewById(R.id.et_user_login);
        et_pass=findViewById(R.id.et_pass_login);
        lblerror=findViewById(R.id.lbl_error);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public void Ingresar(View view){
        try {
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
            url = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/usuario/login.php?rut_usuario="+user;
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
                            i.putExtra("rut", user);
                            startActivity(i);
                            et_user.setText("");
                            et_pass.setText("");
                            et_user.requestFocus();
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

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
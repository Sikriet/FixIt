package com.example.fixit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;


public class registerActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    private EditText et_nombre, et_apellido, et_rut, et_correo, et_numero, et_pass, et_pass2;
    private TextView lblerror;
    String rutU, nombreU, apellidoU, correoU, numeroU, direccionU,passU, pass2U;
    Spinner spinner;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nombre = findViewById(R.id.et_nombre_usuario);
        et_apellido = findViewById(R.id.et_apellido_usuario);
        et_rut = findViewById(R.id.et_rut_usuario);
        et_correo = findViewById(R.id.et_correo_usuario);
        et_numero = findViewById(R.id.et_numero_usuario);
        et_pass = findViewById(R.id.et_pass_usuario);
        et_pass2 = findViewById(R.id.et_pass2_usuario);
        lblerror = findViewById(R.id.lbl_error);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos_vehiculos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        request = Volley.newRequestQueue(getApplicationContext());
    }

    public void RegistrarUsuario(View view) {
            rutU = et_rut.getText().toString();
            nombreU = et_nombre.getText().toString();
            apellidoU = et_apellido.getText().toString();
            correoU = et_correo.getText().toString();
            numeroU = et_numero.getText().toString();
            direccionU = "";
            passU = et_pass.getText().toString();
            pass2U = et_pass2.getText().toString();
            if (rutU.length() == 0 || nombreU.length() == 0 || apellidoU.length() == 0 || correoU.length() == 0 || numeroU.length() == 0 || /*direccionU.length() == 0 ||*/ passU.length() == 0 || pass2U.length() == 0) {
                Snackbar.make(view, "Rellene todos los campos", Snackbar.LENGTH_LONG).show();
            } else if (rutU.length() > 10 & (rutU.length() < 8)) {
                Snackbar.make(view, "Ingrese nuevamente el RUT", Snackbar.LENGTH_LONG).show();
            } else if (numeroU.length() > 9) {
                Snackbar.make(view, "Reingrese su teléfono con el siguiente formato: 9XXXXXXXX", Snackbar.LENGTH_LONG).show();
            } else if (passU.isEmpty()) {
                Snackbar.make(view, "Ingrese una contraseña", Snackbar.LENGTH_LONG).show();
            } else if (!passU.equals(pass2U)) {
                Snackbar.make(view, "Las contraseñas no son iguales", Snackbar.LENGTH_LONG).show();
            } else {
                cargarWebService();
            }
    }
    private void cargarWebService() {
        try{
            String url="https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/usuario/insertar.php?rut_usuario="+rutU+
                    "&nombre_usuario="+nombreU+"&apellido_usuario="+apellidoU+"&email_usuario="+correoU+
                    "&telefono_usuario="+numeroU+"&direccion_usuario="+direccionU+"&contrasena_usuario="+passU+"&estado_usuario=1";
            url = url.replace(" ","%20");
            //lblerror.setText(url.toString());
            jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            request.add(jsonObjectRequest);
        }catch (Exception e){
        lblerror.setText(e.getMessage().toString());
        }
    }

    @Override
    //Se supone que es error pero ta weno SJJSJSS
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        et_rut.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
        et_correo.setText("");
        et_numero.setText("");
        et_pass.setText("");
        et_pass2.setText("");
    }
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "No se pudo registrar ", Toast.LENGTH_SHORT).show();
    }

}
package com.example.fixit;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class profileActivity extends AppCompatActivity {

    private ImageView iv_profileAvatar;
    private EditText et_nombre;
    private EditText et_apellido;
    private EditText et_telefono;
    private EditText et_correo;
    private String rut_logeado;
    UserClass userClass = new UserClass();
    ProgressDialog progressDialog;
    private Button btn_update;
    private Spinner spinner;
    Bitmap bitmap;
    final int SELECT_IMAGE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        iv_profileAvatar = findViewById(R.id.iv_profileAvatar);
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
                editUserImage();
                Intent i = new Intent(getApplicationContext(), menuActivity.class);
                i.putExtra("rut", rut_logeado);
                startActivity(i);
            }
        });

        iv_profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
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
                    userClass.setImg_profileURL(jsonObject.getString("img_usuario"));

                    et_nombre.setText(userClass.getNombre_usuario());
                    et_apellido.setText(userClass.getApellido_usuario());
                    et_telefono.setText(userClass.getTelefono_usuario());
                    et_correo.setText(userClass.getEmail_usuario());
                    String img = userClass.getImg_profileURL();
                    Glide.with(getApplicationContext()).load("https://fixitpage.000webhostapp.com" + img).into(iv_profileAvatar);
                    switch (userClass.getVehiculo_usuario()) {
                        case "Auto":
                            spinner.setSelection(0);
                            break;
                        case "Moto":
                            spinner.setSelection(1);
                            break;
                        case "Camion":
                            spinner.setSelection(2);
                            break;
                    }
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

    public void editUserImage() {
        RequestQueue requestQueue = Volley.newRequestQueue(profileActivity.this);
        String URL = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/subirImagen.php";
        progressDialog = new ProgressDialog(profileActivity.this);
        progressDialog.setTitle("Subiendo");
        progressDialog.setMessage("Por favor espere...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Imagen actualizada correctamente", Toast.LENGTH_LONG).show();
                getUserInfo();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error al guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String imageData = imageToString(bitmap);
                // Entrega de parametros al PHP
                params.put("img_usuario", imageData);
                params.put("rut_usuario", userClass.getRut_usuario());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                iv_profileAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
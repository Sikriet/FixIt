package com.example.fixit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class info_pyme_product extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn_RatingComent;
    private Button btn_llamar;
    private TextView tv_pymeName;
    MyTag selected_pyme = new MyTag();
    List<Producto> productsList = new ArrayList<Producto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pyme_product);

        tv_pymeName = findViewById(R.id.tv_pymeName);
        btn_RatingComent = findViewById(R.id.btn_search);
        btn_llamar = findViewById(R.id.btn_llamar);

        recyclerView = (RecyclerView) findViewById(R.id.rvMechanicsList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        selected_pyme = (MyTag) getIntent().getSerializableExtra("selected_pyme");
        getProducts(selected_pyme.getId_pyme());

        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + selected_pyme.getTelefono_pyme()));
                startActivity(intent);
            }
        });

        btn_RatingComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(info_pyme_product.this, valoracionActivity.class);
                i.putExtra("selected_pyme", (Serializable) selected_pyme);
                startActivity(i);
            }
        });
    }

    public void getProducts(String id_pyme) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "https://" + getResources().getString(R.string.hostname) + ".000webhostapp.com/api/solicitudes/producto/seleccionar.php?id_pyme=" + id_pyme;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);


                    if (jsonArray.length() > 0) {
                        tv_pymeName.setText(selected_pyme.getPymeName());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Producto producto = new Producto();
                            producto.setId_producto(jsonObject.getString("id_producto"));
                            producto.setNombre_producto(jsonObject.getString("nombre_producto"));
                            producto.setPrecio_producto(jsonObject.getString("precio_producto"));
                            producto.setId_pyme(jsonObject.getString("id_pyme"));
                            productsList.add(producto);
                            mAdapter = new RecyclerViewAdapter(productsList, info_pyme_product.this);
                            recyclerView.setAdapter(mAdapter);
                        }
                    } else {
                        tv_pymeName.setText("No se ha encontrado ningún servicio");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }
}
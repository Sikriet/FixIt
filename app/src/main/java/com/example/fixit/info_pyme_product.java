package com.example.fixit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class info_pyme_product extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn_RatingComent;

    List<Producto> productsList = new ArrayList<Producto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pyme_product);

        btn_RatingComent = findViewById(R.id.btnRatingComent);

        recyclerView = (RecyclerView) findViewById(R.id.rvMechanicsList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getProducts();

        btn_RatingComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(info_pyme_product.this, valoracionActivity.class);
                startActivity(i);
            }
        });
    }

    public void getProducts() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "https://supernaturalism-sho.000webhostapp.com/api/solicitudes/producto/seleccionar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    Producto producto = new Producto();
                    producto.setId_producto(jsonObject.getString("id_producto"));
                    producto.setNombre_producto(jsonObject.getString("nombre_producto"));
                    producto.setPrecio_producto(jsonObject.getString("precio_producto"));
                    producto.setCantidad_producto(jsonObject.getString("cantidad_producto"));
                    producto.setId_pyme(jsonObject.getString("id_pyme"));
                    productsList.add(producto);
                    mAdapter = new RecyclerViewAdapter(productsList, info_pyme_product.this);
                    recyclerView.setAdapter(mAdapter);
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
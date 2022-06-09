package com.example.fixit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class menuActivity extends AppCompatActivity implements OnMapReadyCallback{

    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_search = findViewById(R.id.btn_search);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(menuActivity.this, info_pyme_product.class);
                startActivity(i);
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Set the map coordinates to Concepción
        LatLng concepcion = new LatLng(-36.820, -73.044);
        // Set the map type to Hybrid
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Move the camera to the map coordinates and zoom in closer
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(concepcion));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        // Display traffic
        googleMap.setTrafficEnabled(true);
        // Button for "My Location"
        googleMap.setMyLocationEnabled(true);
    }
}
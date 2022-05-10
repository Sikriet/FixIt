package com.example.fixit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
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
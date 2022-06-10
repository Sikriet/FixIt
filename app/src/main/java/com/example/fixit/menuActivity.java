package com.example.fixit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class menuActivity extends AppCompatActivity implements OnMapReadyCallback{

    private Button btn_search;
    String addressName;
    List<String> streetList = new ArrayList<>();
    ArrayList<Marker> markers = new ArrayList<>();

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
        // Set the map type to Hybrid
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Move the camera to the map coordinates and zoom in closer
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(concepcion));
        //googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        // Display traffic
        googleMap.setTrafficEnabled(true);
        // Button for "My Location"
        googleMap.setMyLocationEnabled(true);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://my-json-server.typicode.com/sikriet/cities/topics";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        // Add Markers
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = new JSONObject(jsonArray.getJSONObject(i).toString());
                            addressName = jsonObject.getString("name");
                            streetList.add(addressName);
                            LatLng pymeUbication = getLocationFromAddress(getApplicationContext(), streetList.get(i));
                            Marker marker = googleMap.addMarker(new MarkerOptions()
                                    .position(pymeUbication)
                                    .title("Nombre Pyme"));
                            markers.add(marker);
                        }

                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        for (Marker marker : markers) {
                            builder.include(marker.getPosition());
                        }
                        LatLngBounds bounds = builder.build();

                        int padding = 75;
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

                        googleMap.animateCamera(cu);
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
    
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder geocoder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
            address = geocoder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }
}
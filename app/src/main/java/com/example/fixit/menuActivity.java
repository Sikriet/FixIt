package com.example.fixit;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class menuActivity extends AppCompatActivity implements OnMapReadyCallback, OnMarkerClickListener {

    private Button btn_search;
    private FloatingActionButton floatActBtn_profile;
    String id_pyme;
    String addressName;
    String pymeName;
    String comuna_pyme;
    String rut_logeado;
    String telefono_pyme;
    MyTag selected_pyme = new MyTag();
    List<String> streetList = new ArrayList<>();
    ArrayList<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_search = findViewById(R.id.btn_search);
        floatActBtn_profile = findViewById(R.id.floatActBtn_profile);
        rut_logeado = getIntent().getExtras().getString("rut");

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected_pyme.getId_pyme() == null) {
                    Toast.makeText(menuActivity.this, "Seleccione una pyme para continuar", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), info_pyme_product.class);
                    i.putExtra("selected_pyme", (Serializable) selected_pyme);
                    startActivity(i);
                }
            }
        });

        floatActBtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), profileActivity.class);
                i.putExtra("rut", rut_logeado);
                startActivity(i);
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Set the map type to Hybrid
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Display traffic
        googleMap.setTrafficEnabled(true);
        // Button for "My Location"
        googleMap.setMyLocationEnabled(true);
        // Button for "Zoom"
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://fixitpage.000webhostapp.com/api/solicitudes/pyme/seleccionar.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        // Add Markers
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = new JSONObject(jsonArray.getJSONObject(i).toString());
                            id_pyme = jsonObject.getString("id_pyme");
                            addressName = jsonObject.getString("direccion_pyme");
                            pymeName = jsonObject.getString("nombre_pyme");
                            comuna_pyme = jsonObject.getString("comuna_pyme");
                            telefono_pyme = jsonObject.getString("telefono_pyme");
                            streetList.add(addressName + ", " + comuna_pyme);
                            LatLng pymeUbication = getLocationFromAddress(getApplicationContext(), streetList.get(i));
                            Marker marker = googleMap.addMarker(new MarkerOptions()
                                    .position(pymeUbication)
                                    .title(pymeName));
                            if (marker != null) {
                                MyTag myTag = new MyTag(id_pyme, pymeName, telefono_pyme);
                                marker.setTag(myTag);
                            }
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
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        selected_pyme = (MyTag) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (selected_pyme != null) {
            selected_pyme.setId_pyme(selected_pyme.id_pyme);
            selected_pyme.setPymeName(selected_pyme.pymeName);
            selected_pyme.setTelefono_pyme(selected_pyme.telefono_pyme);
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
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
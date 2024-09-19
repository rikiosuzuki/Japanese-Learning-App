package com.example.suzuki_r_finalproject;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeographyActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geography);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void handleGoBack(View view) {
        finish();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng tokyo = new LatLng(35.6895, 139.6917); // Coordinates for Tokyo
        googleMap.addMarker(new MarkerOptions().position(tokyo).title("Marker in Tokyo"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 10)); // Zoom level 10
    }
}
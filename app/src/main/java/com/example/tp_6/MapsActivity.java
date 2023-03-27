package com.example.tp_6;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tp_6.model.Antenne;
import com.example.tp_6.model.ClusterItemClass;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tp_6.databinding.ActivityMapsBinding;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ArrayList<Antenne> antennes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentMain = getIntent();
        antennes = intentMain.getParcelableArrayListExtra("antennes");
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ClusterManager<ClusterItemClass> clusterManager = new ClusterManager<>(this, mMap);

        for (Antenne antenne : antennes) {
            LatLng position = new LatLng(antenne.getFields().getGeo_point_2d().get(0), antenne.getFields().getGeo_point_2d().get(1));
            ClusterItemClass item = new ClusterItemClass(position.latitude, position.longitude, antenne.getFields().getOp_name() + ' ' + antenne.getFields().getOp_code(), null);
            clusterManager.addItem(item);
        }

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        LatLng paris = new LatLng(48.8566, 2.3522); // coordonnées de Paris
        float zoomLevel = 10; // zoom de départ
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, zoomLevel));

        MyClusterRenderer renderer = new MyClusterRenderer(this, mMap, clusterManager);
        clusterManager.setRenderer(renderer);
    }





}


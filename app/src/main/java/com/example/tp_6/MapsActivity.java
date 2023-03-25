package com.example.tp_6;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tp_6.model.Antenne;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tp_6.databinding.ActivityMapsBinding;

import java.util.ArrayList;

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
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (Antenne antenne : antennes){
            LatLng map = new LatLng(antenne.getFields().getGeo_point_2d().get(0), antenne.getFields().getGeo_point_2d().get(1));
            mMap.addMarker(new MarkerOptions().position(map).title(antenne.getFields().getOp_name() + ' ' + antenne.getFields().getOp_code()));
        }
        LatLng point = new LatLng(48.50,2.20);
        float zoomLevel = 5;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, zoomLevel));
    }
}
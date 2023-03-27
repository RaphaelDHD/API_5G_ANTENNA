package com.example.tp_6;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ArrayList<Antenne> antennes = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 10;


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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
        ClusterManager<ClusterItemClass> clusterManager = new ClusterManager<>(this, mMap);

        for (Antenne antenne : antennes) {
            LatLng position = new LatLng(antenne.getFields().getGeo_point_2d().get(0), antenne.getFields().getGeo_point_2d().get(1));
            ClusterItemClass item = new ClusterItemClass(position.latitude, position.longitude, antenne.getFields().getOp_name() + ' ' + antenne.getFields().getOp_code(), null);
            clusterManager.addItem(item);
        }

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        /*LatLng paris = new LatLng(48.8566, 2.3522); // coordonnées de Paris
        float zoomLevel = 10; // zoom de départ
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, zoomLevel)); */

        MyClusterRenderer renderer = new MyClusterRenderer(this, mMap, clusterManager);
        clusterManager.setRenderer(renderer);
    }

    private void setUpMap() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); // Activer le bouton de localisation sur la carte
            mMap.getUiSettings().setMyLocationButtonEnabled(true); // Activer la localisation
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    // Récupérer la position actuelle de l'utilisateur
                    LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());

                    // Ajouter un marqueur à la position de l'utilisateur
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("Ma position"));

                    // Zoomer sur la position de l'utilisateur
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10));
                }
            });
        } else {
            // Demander la permission de localisation à l'utilisateur
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, MY_PERMISSIONS_REQUEST_LOCATION);

            // Déplacer la carte sur Paris
            LatLng paris = new LatLng(48.8534, 2.3488);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, 10));
        }
    }




}


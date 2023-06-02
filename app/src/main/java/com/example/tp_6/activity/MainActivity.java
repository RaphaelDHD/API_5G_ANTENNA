package com.example.tp_6.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_6.AntenneAdapter;
import com.example.tp_6.R;
import com.example.tp_6.manager.mainController;
import com.example.tp_6.model.Antenne;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private com.example.tp_6.manager.mainController mainController = new mainController();

    private ArrayList<Antenne> antennes = new ArrayList<>();
    private ArrayList<Antenne> favoris = new ArrayList<>();
    private AntenneAdapter adapter;
    private boolean loadAlreadyDone = false;
    private boolean firstTestInternetisAvailable = false;
    private final int SETTINGS_REQUEST_CODE = 2;
    private final int DETAILED_REQUEST_CODE = 3;
    private final int DETAILED_RESULT_CODE = 350;
    private final int SETTINGS_RESULT_CODE = 250;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (mainController.isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        adapter = new AntenneAdapter(antennes,getApplicationContext());
        setContentView(R.layout.activity_main);
        if (isNetworkAvailable()){
            loadAlreadyDone = true;
            mainController.addAllAntenna(antennes,adapter);
        }
        else {
            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();

        }
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        createListOperator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, DetailedActivity.class);
                myIntent.putExtra("antenne",antennes.get(position));
                myIntent.putExtra("favoris",favoris);
                startActivityForResult(myIntent,DETAILED_REQUEST_CODE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (antennes.size() != favoris.size() || !antennes.containsAll(favoris)) {
                    Antenne antenne = antennes.get(position);
                    favoris.add(antenne);
                    Toast.makeText(getApplicationContext(),antenne.getFields().getOp_name() + " " + antenne.getFields().getOp_site_id()
                            + " a été ajouté aux favoris !",Toast.LENGTH_LONG).show();

                }
                return false;
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onClickMap(View view){
        Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
        myIntent.putExtra("antennes",antennes);
        startActivity(myIntent);
    }


   private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable( Network network) {
            super.onAvailable(network);
            if (!loadAlreadyDone){
                Spinner spinner = findViewById(R.id.spinnerMain);
                int selectedSpinner = spinner.getSelectedItemPosition();
                switch (selectedSpinner){
                    case 0 :
                        mainController.addAllAntenna(antennes,adapter);
                        break;
                    case 1 :
                        mainController.addOrangeAntenna(antennes, adapter);
                        break;
                    case 2 :
                        mainController.addSFRAntenna(antennes,adapter);
                        break;
                    case 3 :
                        mainController.addBouyguesAntenna(antennes,adapter);
                        break;
                    case 4 :
                        mainController.addFreeAntenna(antennes,adapter);
                        break;
                    case 5 :
                        mainController.showFavs(antennes,favoris,adapter);
                        break;
                }
            }
            if (firstTestInternetisAvailable){
                Toast.makeText(getApplicationContext(),"De nouveau connecter a internet",Toast.LENGTH_LONG).show();
            }
            else {
                firstTestInternetisAvailable = true;
            }
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            Toast.makeText(getApplicationContext(),"Connexion internet perdue",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCapabilitiesChanged(Network network,NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
        }
    };

    public void onClickSettings(View view){
        Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
        myIntent.putExtra("isDarkTheme",mainController.isDarkTheme());
        startActivityForResult(myIntent,SETTINGS_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SETTINGS_REQUEST_CODE && data != null)
        {
            if (resultCode == SETTINGS_RESULT_CODE) {
                boolean message = data.getBooleanExtra("isDarkTheme", false);
                mainController.setDarkTheme(message);
            }
        }
        if(requestCode==DETAILED_REQUEST_CODE && data != null)
        {
            if (resultCode == DETAILED_RESULT_CODE) {
                Log.d("test","retour de result");
            }
        }
    }

    public void createListOperator() {
        Spinner spinner = findViewById(R.id.spinnerMain);
        if (spinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.OpArray,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        } else {
            Log.e("createListOperator", "Spinner is null");
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id){
                    case 0 :
                        if (isNetworkAvailable()){
                            mainController.addAllAntenna(antennes,adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();

                        }
                        break;
                    case 1:
                        if (isNetworkAvailable()){
                            mainController.addOrangeAntenna(antennes,adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        if (isNetworkAvailable()){
                            mainController.addSFRAntenna(antennes,adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();
                        }
                        break;

                    case 3:
                        if (isNetworkAvailable()){
                            mainController.addBouyguesAntenna(antennes,adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();
                        }
                        break;

                    case 4:
                        if (isNetworkAvailable()){
                            mainController.addFreeAntenna(antennes,adapter);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Aucune connexion internet. Impossible de charger les informations",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5:
                        mainController.showFavs(antennes,favoris,adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
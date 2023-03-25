package com.example.tp_6.manager;

import android.util.Log;

import com.example.tp_6.model.Antenne;
import com.example.tp_6.AntenneAdapter;
import com.example.tp_6.AntenneResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class mainController {
    private final APIManager apiManager;
    private boolean isDarkTheme = false;

    public mainController() {
        apiManager = APIManager.getInstance();
    }

    public void setDarkTheme(boolean darkTheme) {
        isDarkTheme = darkTheme;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }

    public void addAllAntenna(ArrayList<Antenne> antennes, AntenneAdapter adapter){
        Log.d("test","ici");
        Call<AntenneResponse> call = apiManager.getAntenneService().getRecords();
        call.enqueue(new Callback<AntenneResponse>() {
            @Override
            public void onResponse(Call<AntenneResponse> call, Response<AntenneResponse> response) {
                if(response.isSuccessful()){
                    AntenneResponse resp = response.body();
                    antennes.addAll(Arrays.asList(resp.getRecords()));
                    Log.d("test","Success");
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AntenneResponse> call, Throwable t) {
                // do something on failure
            }
        });




    }



}

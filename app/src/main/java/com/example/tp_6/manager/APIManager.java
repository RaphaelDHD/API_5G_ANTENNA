package com.example.tp_6.manager;

import com.example.tp_6.service.AntenneService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    final static String BASE_URL = "https://public.opendatasoft.com/api/records/1.0/search/";
    private AntenneService antenneService = null;
    private static APIManager instance;

    public AntenneService getAntenneService() {
        return antenneService;
    }

    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }

        return instance;
    }


    private APIManager() {
        createRetrofitAntenne();
    }

    private void createRetrofitAntenne() {

        Retrofit retrofitAntenne = new Retrofit.Builder()
                .baseUrl("https://public.opendatasoft.com/api/records/1.0/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        antenneService = retrofitAntenne.create(AntenneService.class);

    }


}

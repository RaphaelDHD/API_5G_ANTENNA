package com.example.tp_6.service;

import com.example.tp_6.AntenneResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface AntenneService {
    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800")
    Call<AntenneResponse> getRecords();
}

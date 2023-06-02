package com.example.tp_6.service;

import com.example.tp_6.manager.AntenneResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface AntenneService {
    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800")
    Call<AntenneResponse> getRecords();

    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800&refine.op_name=Orange")
    Call<AntenneResponse> getOrangeRecords();

    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800&refine.op_name=Société+française+du+radiotéléphone")
    Call<AntenneResponse> getSFRRecords();

    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800&refine.op_name=Bouygues+Telecom")
    Call<AntenneResponse> getBouyguesRecords();

    @GET("?dataset=buildingref-france-arcep-mobile-site-5g&q=&rows=800&refine.op_name=Free+mobile")
    Call<AntenneResponse> getFreeRecords();
}

package com.example.koichung2.Networks;

import com.example.koichung2.Model.Batch.Batch;
import com.example.koichung2.Model.Login.LoginRespone;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServer {

    //login
    @POST("api/Service/CheckLogin")
    @Headers("Content-Type:application/json")
    Call<LoginRespone> getUser(@Body JsonObject jsonObject);

    //get list batch
    @POST("api/Service/CheckLogin")
    @Headers("Content-Type:application/json")
    Call<Batch> getListBatch(@Body JsonObject jsonObject);



}

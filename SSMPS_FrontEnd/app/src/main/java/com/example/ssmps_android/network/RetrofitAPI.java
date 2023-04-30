package com.example.ssmps_android.network;

import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Manager;
import com.example.ssmps_android.dto.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("api/join")
    Call<Manager> join(@Body Manager manager);

    @GET("api/manager/login")
    Call<String> login(@Body LoginRequest loginRequest);

    @POST("api/manager/test")
    Call<String> testPosting();
}
package com.example.ssmps_android.network;

import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Manager;
import com.example.ssmps_android.dto.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @POST("api/join")
    Call<Manager> join(@Body Manager manager);

    @GET("api/manager/login")
    Call<String> login(@Query("id") String id, @Query("password") String password);

    @POST("api/manager/test")
    Call<String> testPosting();

    @GET("api/loginFirst")
    Call<String> loginFirst(@Query("id") String id, @Query("password") String password);
}
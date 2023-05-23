package com.example.ssmps_android;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.Recyclerview.CustomAdapter;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.Manager;
import com.example.ssmps_android.domain.Store;
import com.example.ssmps_android.dto.StoreResponse;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreSelectActivity extends AppCompatActivity {
    SharedPreferenceUtil sharedPreferenceUtil;
    TokenInterceptor tokenInterceptor;

    Retrofit retrofit;
    RetrofitAPI service;
    Gson gson;

    Manager nowManager;

    List<Store> storeList = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_select);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();
        getStorelistData();

//        ArrayList<Store> testDataSet = new ArrayList<>();
//        for (int i =0; i<20; i++) {
//            testDataSet.add(new Store(Long.valueOf(i), i + "번 매장", "천안", null));
//        }
    }

    private void initData(){
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);
        gson = new GsonBuilder().create();

        nowManager = gson.fromJson(sharedPreferenceUtil.getData("manager", "err"), Manager.class);
    }

    private void getStorelistData(){
        Call<List<StoreResponse>> findStoreList = service.findStoreList(nowManager.getId());
        findStoreList.enqueue(new Callback<List<StoreResponse>>() {
            @Override
            public void onResponse(Call<List<StoreResponse>> call, Response<List<StoreResponse>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(StoreSelectActivity.this, "매장 불러오기 에러", Toast.LENGTH_SHORT).show();
                    Log.e("store finding error", response.errorBody().toString());
                    return;
                }
                Log.e("store finding success", "매장 불러오기 성공");
                storeList = response.body().stream()
                        .map(s -> new Store(s.getId(), s.getName(), s.getAddress(), null))
                        .collect(Collectors.toList());
                Log.e("store size", storeList.size() + "");
                setRecyclerView();
            }

            @Override
            public void onFailure(Call<List<StoreResponse>> call, Throwable t) {
                Toast.makeText(StoreSelectActivity.this, "매장 불러오기 실패", Toast.LENGTH_SHORT).show();
                Log.e("store finding", "error");
            }
        });
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.storeSelect_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new CustomAdapter(storeList, getApplicationContext());

        Log.e("store list", storeList.size() + "");
        recyclerView.setAdapter(customAdapter);
    }
}

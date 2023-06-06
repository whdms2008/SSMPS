package com.example.ssmps_android.manager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.R;
import com.example.ssmps_android.Recyclerview.ManagerRegistSearchAdapter;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.CenterItem;
import com.example.ssmps_android.dto.CenterItemResponse;
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

public class ManagerSearchCenterItemActivity extends AppCompatActivity {
    EditText itemNameInput;
    Button searchBtn;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;
    String token;

    List<CenterItem> centerItemList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_item_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();
        getAllCenterItem();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에서 데이터 가져오기
                searchItem();
            }
        });
    }
    
    private void initData(){
        //itemNameInput = findViewById(R.id.managerSearchItem_item_name_input);
        searchBtn = findViewById(R.id.managerSearchItem_search_btn);

        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        gson = new GsonBuilder().create();
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);
    }

    private void setToken(){
        token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void getAllCenterItem(){
        Call<List<CenterItemResponse>> getAllItem = service.findAllCenterItem();
        getAllItem.enqueue(new Callback<List<CenterItemResponse>>() {
            @Override
            public void onResponse(Call<List<CenterItemResponse>> call, Response<List<CenterItemResponse>> response) {
                if(!response.isSuccessful()){
                    Log.e("getAllItem Error", response.errorBody().toString());
                    Toast.makeText(ManagerSearchCenterItemActivity.this, "아이템 리스트 가져오기 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ManagerSearchCenterItemActivity.this, "리스트업 성공!", Toast.LENGTH_SHORT).show();
                centerItemList = response.body().stream()
                        .map(ci -> new CenterItem(ci.getId(), ci.getName(), ci.getType(), ci.getPrice(), ci.getImage(), ci.getBarcode()))
                        .collect(Collectors.toList());
                setItemRecyclerview();
            }

            @Override
            public void onFailure(Call<List<CenterItemResponse>> call, Throwable t) {
                Log.e("getAllItem fail", t.getMessage());
                Toast.makeText(ManagerSearchCenterItemActivity.this, "아이템 리스트 가져오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void searchItem(){
        String itemName = itemNameInput.getText().toString();
        Log.e("itemName", itemName);
        Call<List<CenterItemResponse>> findItemByName = service.findCenterItemByName(itemName);
        findItemByName.enqueue(new Callback<List<CenterItemResponse>>() {
            @Override
            public void onResponse(Call<List<CenterItemResponse>> call, Response<List<CenterItemResponse>> response) {
                if(!response.isSuccessful()){
                    Log.e("get search item", response.errorBody().toString());
                    Toast.makeText(ManagerSearchCenterItemActivity.this, "아이템 검색 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ManagerSearchCenterItemActivity.this, "아이템 검색 성공", Toast.LENGTH_SHORT).show();
                centerItemList = response.body().stream()
                        .map(ci -> new CenterItem(ci.getId(), ci.getName(), ci.getType(), ci.getPrice(), ci.getImage(), ci.getBarcode()))
                        .collect(Collectors.toList());
                setItemRecyclerview();
            }

            @Override
            public void onFailure(Call<List<CenterItemResponse>> call, Throwable t) {
                Log.e("get search Item fail", t.getMessage());
                Toast.makeText(ManagerSearchCenterItemActivity.this, "아이템 검색 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setItemRecyclerview(){
        RecyclerView recyclerView = findViewById(R.id.itemSearchManager_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ManagerRegistSearchAdapter adapter = new ManagerRegistSearchAdapter(centerItemList);
        recyclerView.setAdapter(adapter);
    }
}
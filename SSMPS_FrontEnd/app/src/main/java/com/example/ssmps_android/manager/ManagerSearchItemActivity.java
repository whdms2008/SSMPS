package com.example.ssmps_android.manager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssmps_android.R;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.CenterItem;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Store;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManagerSearchItemActivity extends AppCompatActivity {
    EditText itemNameInput;
    Button searchBtn;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;
    String token;
    List<Item> itemList = new ArrayList<>();
    Store nowStore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_manager);
        initData();
        getAllItem();
    }

    private void initData() {
        itemNameInput = findViewById(R.id.managerSearchItem_item_name_input);
        searchBtn = findViewById(R.id.managerSearchItem_search_btn);

        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        gson = new GsonBuilder().create();
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);

        nowStore = gson.fromJson(sharedPreferenceUtil.getData("store", "err"), Store.class);
    }
    private void setToken(){
        token = sharedPreferenceUtil.getData("token", "err");
        Log.e("token", token);
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void getAllItem(){
        Call<List<Item>> findAllItem = service.findAllItem(nowStore.getId());
        findAllItem.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    Log.e("find all item error", response.errorBody().toString());
                    Toast.makeText(ManagerSearchItemActivity.this, "물건 리스트 가져오기 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                itemList = response.body();
                Log.e("list size", itemList.size() + "");
                setRecyclerviewData();
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("find all item fail", t.getMessage());
                Toast.makeText(ManagerSearchItemActivity.this, "물건 리스트 가져오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerviewData(){

    }
}

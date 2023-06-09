package com.example.ssmps_android.guest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssmps_android.R;
import com.example.ssmps_android.Recyclerview.CustomAdapter4;
import com.example.ssmps_android.data.SharedPreferenceUtil;
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

public class GuestItemListActivity extends AppCompatActivity {
    TextView itemName;
    List<Item> itemList = new ArrayList<>();

    Button searchBtn;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;
    String token;
    Store nowStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_guest_item_list);
        initData();
        setListData();
    }

    private void setRecyclerview(){
        RecyclerView recyclerView = findViewById(R.id.guestItemList_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter4 customAdapter4 = new CustomAdapter4(itemList);
        recyclerView.setAdapter(customAdapter4);
    }

    private void initData(){
        itemName = findViewById(R.id.guestItemList_name);
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        gson = new GsonBuilder().create();
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);

        nowStore = gson.fromJson(sharedPreferenceUtil.getData("store", "err"), Store.class);
    }

    private void setToken(){
        token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void setListData(){
        Call<List<Item>> findAllItem = service.findAllItem(nowStore.getId());
        findAllItem.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    Log.e("find all item error", response.errorBody().toString());
                    Toast.makeText(GuestItemListActivity.this, "매장 물건 가져오기 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                itemList = response.body();
                setRecyclerview();
            }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("find all item fail", t.getMessage());
                Toast.makeText(GuestItemListActivity.this, "매장 물건 가져오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
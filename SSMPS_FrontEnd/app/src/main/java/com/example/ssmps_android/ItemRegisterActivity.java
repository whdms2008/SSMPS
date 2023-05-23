package com.example.ssmps_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.CenterItem;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Store;
import com.example.ssmps_android.dto.RegistItemRequest;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemRegisterActivity extends AppCompatActivity{
    EditText itemName, itemQuantity, itemType;
    ImageView itemImage;
    Button registerBtn;
    CenterItem nowItem;
    Store nowStore;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;
    String token;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initData();
        setItemData();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerItem();
            }
        });
    }

    private void initData(){
        itemName = findViewById(R.id.itemRegister_item_name);
        itemQuantity = findViewById(R.id.itemRegister_quantity);
        itemType = findViewById(R.id.itemRegister_quantity);

        itemImage = findViewById(R.id.itemRegister_item_img);
        registerBtn = findViewById(R.id.itemRegister_register_btn);

        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);
        gson = new GsonBuilder().create();


        nowStore = gson.fromJson(sharedPreferenceUtil.getData("store", "err"), Store.class);
    }

    private void setToken(){
        token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void setItemData(){
        Intent intent = getIntent();
        nowItem = (CenterItem) intent.getSerializableExtra("item");
        itemName.setText(nowItem.getName());
        itemType.setText(nowItem.getType());
//        itemImage.setImageBitmap();
        // 이미지 blob -> Bitmap로 바꿔서 등록
    }

    private void registerItem(){
        Call<Item> registItem = service.registItem(nowStore.getId(), nowItem.getId());
        registItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if(!response.isSuccessful()){
                    Log.e("regist item error", response.errorBody().toString());
                    Toast.makeText(ItemRegisterActivity.this, "아이템 등록 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                Item registedItem = response.body();
                Toast.makeText(ItemRegisterActivity.this, registedItem.getName() + "이 등록되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e("regist item fail", t.getMessage());
                Toast.makeText(ItemRegisterActivity.this, "아이템 등록 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
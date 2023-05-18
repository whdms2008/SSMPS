package com.example.ssmps_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.dto.LoginRequest;
import com.example.ssmps_android.login.GuestActivity;
import com.example.ssmps_android.login.SignActivity;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText idInput, passInput;
    TextView guestBtn, joinBtn;
    Button loginBtn;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        initComponent();
        join();
        guest_login();

        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(intent);
    }
    private void initComponent(){
        idInput = findViewById(R.id.login_id);
        passInput = findViewById(R.id.login_password);
        guestBtn = findViewById(R.id.mainActivity_login_guest);
        joinBtn = findViewById(R.id.login_join);
        loginBtn = findViewById(R.id.login_login_btn);

        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);
    }

    private void setToken(){
        String token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void login(){
        String id = idInput.getText().toString();
        String password = passInput.getText().toString();

        loginBtn.setOnClickListener(v -> {
            LoginRequest loginRequest = new LoginRequest(id, password);
            Call<String> login = service.login(loginRequest);
            login.enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(MainActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
                    try {
                        assert response.errorBody() != null;
                        Log.e("login fail", response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "로그인 실패!", Toast.LENGTH_SHORT).show();
                    Log.e("login error", t.getMessage());
                }
            });
        });
    }

    private void join(){
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ㅇㅇ","v");
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(intent);
            }
        });

    }
    private void guest_login(){
        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StoreSelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
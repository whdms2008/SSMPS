package com.example.ssmps_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


// 테스트용 페이지
public class MainPage extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);

        
        
        
        findViewById(R.id.mainPage_regist_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> test = service.testPosting();
                test.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(MainPage.this, "테스트 성공", Toast.LENGTH_SHORT).show();
                            Log.e("test", response.body());
                            return;
                        }
                        if(response.code() == 401){
                            Toast.makeText(MainPage.this, "토큰 만료!!~", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainPage.this, "테스트 에러", Toast.LENGTH_SHORT).show();
                        Log.e("test", t.getMessage());
                    }
                });
            }
        });
    }
    private void setToken(){
        String token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }
    
    
    
}
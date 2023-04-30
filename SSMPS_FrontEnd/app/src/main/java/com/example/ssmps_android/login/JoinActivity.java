package com.example.ssmps_android.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ssmps_android.R;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.Manager;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinActivity extends AppCompatActivity {
    EditText idInput, passInput, passCheckInput;
    Button joinBtn;

    Retrofit retrofit;
    RetrofitAPI service;

    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initComponent();
    }

    private void initComponent(){
        idInput = findViewById(R.id.join_id);
        passInput = findViewById(R.id.join_password);
        passCheckInput = findViewById(R.id.join_password_check);
        joinBtn = findViewById(R.id.join_join_btn);

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

    private void join(){
        String id = idInput.getText().toString();
        String password = passInput.getText().toString();
        String passwordCheck = passCheckInput.getText().toString();



        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.equals(passwordCheck)){
                    Toast.makeText(JoinActivity.this, "비밀번호가 같지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(id.isEmpty() || password.isEmpty()){
                    Toast.makeText(JoinActivity.this, "아이디 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Manager newManager = new Manager(id, password, null);
                Call<Manager> join = service.join(newManager);
                join.enqueue(new Callback<Manager>() {
                    @Override
                    public void onResponse(Call<Manager> call, Response<Manager> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Manager> call, Throwable t) {
                        Toast.makeText(JoinActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                        Log.e("join error", t.getMessage());
                    }
                });
            }
        });
    }
}
package com.example.ssmps_android.location;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssmps_android.R;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.Location;
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

// 테스트용 페이지
public class MainPage extends AppCompatActivity {
    TextView textView;
    Button registerBtn, removeBtn, saveBtn;
    Retrofit retrofit;
    RetrofitAPI service;
    TokenInterceptor tokenInterceptor;
    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;
    Canvas canvas;
    Paint paint;
    float endX, endY;
    float startX = 0.0f, startY = 0.0f;
    boolean flag = false; // 생성버튼이 눌렸나?
    boolean isIn = false; // 내부가 클릭 되었는가?
    boolean isMove = false; // 움직였나?
    boolean isRemove = false; // 삭제 버튼이 눌렸는가?
    boolean isRegister = false; // 물건 등록 버튼이 눌렸는가?
    int clickCnt = 0; // 시작점, 끝점 구분
    List<Location> locationList = new ArrayList<>();
    Location moveLocation, nowLocation;
    ImageView frame;
    float preX, preY; // 이동 전 좌표
    Store nowStore;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store_layout);
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        setToken();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        initData();
        addItem();
        removeLocation();
        registItem();
        addLocation();
        editLocation();
        setLocationList();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registLocation();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void editLocation(){
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.e("flag", flag + "");
                        Log.e("isRemove", isRemove + "");
                        if(!flag && !isRemove && !isRegister){
                            // move
                            if(isClickLocation(event.getX(), event.getY())){
                                preX = event.getX();
                                preY = event.getY();
                            }
                            flag = false;
                            return true;
                        }
                        if(isRemove){
                            // 삭제
                            Log.e("ge", isClickLocation(event.getX(), event.getY()) + "");
                            if(isClickLocation(event.getX(), event.getY())){
                                drawLocation(Color.BLACK, moveLocation);
                                locationList.remove(moveLocation);

                            }
                            isRemove = false;
                            textView.setText("매장 수정입니다~");
                            return true;
                        }
                        if(isRegister){
                            // 물건 등록
                            if(isClickLocation(event.getX(), event.getY())){
                                Log.e("register", "등록");
                                drawLocation(Color.YELLOW, moveLocation);
                                textView.setText("매장 수정입니다~");
                                isRegister = false;
                                Intent intent = new Intent(getApplicationContext(), LocationDetailActivity.class);
                                intent.putExtra("location", moveLocation);
                                startActivity(intent);
                                return true;
                            }
                            return true;
                        }

                        Log.e("cnt", clickCnt + " ");
                        if(clickCnt == 0){
                            startX = event.getX();
                            startY = event.getY();
                            Log.e("start", startX + "  " + startY);
                            clickCnt = 1;
                            textView.setText("끝 점을 선택하세요");
                        }else{
                            Log.e("here", clickCnt + "");
                            endX = event.getX();
                            endY = event.getY();
                            Log.e("endX", endX + "  " + endY);
                            Location location = new Location(startX, startY, endX, endY);
                            locationList.add(location);
                            nowLocation = location;
//                            canvas.drawRect(startX, startY, endX, endY, paint);
//                            frame.invalidate();
                            drawLocation(Color.WHITE, location);
                            clickCnt = 0;
                            textView.setText("매장 수정입니다~");
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if((!flag) && isIn){
                            for (Location location : locationList) {
                                if(location == moveLocation){
                                    continue;
                                }
                                Log.e("움직임", moveLocation.getStartX() + "");
//                                if(location.intersect(moveLocation)){
//                                    // location끼리 겹치지 않도록
////                                    Log.e("겹침?", location.getStartX() + "");
////                                    moveLocation.setStartX(moveLocation.getStartX() + 1);
////                                    moveLocation.setStartY(moveLocation.getStartY() + 1);
////                                    moveLocation.setEndX(moveLocation.getEndX() - 1);
////                                    moveLocation.setEndY(moveLocation.getEndY() - 1);
//                                    break;
//                                }
                            }
//                            Log.e("move", "ee");
//                            event.getX();
//                            moveLocation.setStartX(event.getX()     );
//                            canvas.drawRect(moveLocation.getStartX(), moveLocation.getStartY(), moveLocation.getEndX(), moveLocation.getEndY(), paint);
                            moveLocation(moveLocation, event);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("clickCnt", clickCnt + "");
                        if(clickCnt == 0){
                            flag = false;
                        }
                        if(isMove){
                            drawLocation(Color.WHITE, moveLocation);
                            setLocationData(moveLocation);
                            isMove = false;
                        }
                        isIn = false;

                        break;
                }
                return true;
            }
        });
    }

    private void initData(){
        saveBtn = findViewById(R.id.mainPage_save_btn);
        registerBtn = findViewById(R.id.mainPage_regist_item_btn);
        removeBtn = findViewById(R.id.mainPage_remove_btn);
        textView = findViewById(R.id.textView);

        Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);

        frame = findViewById(R.id.mainPage_draw_space);
        frame.setImageBitmap(bitmap);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        gson = new GsonBuilder().create();
        setToken();

        retrofit = RetrofitClient.getInstance(tokenInterceptor);
        service = retrofit.create(RetrofitAPI.class);

        nowStore = gson.fromJson(sharedPreferenceUtil.getData("store", "err"), Store.class);
        Log.e("now store loc", nowStore.getLocaiton().size() + "");
    }


    private void setLocationList(){
        Call<List<Location>> findLocation = service.findStoreLocation(nowStore.getId());
        findLocation.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(!response.isSuccessful()){
                    Log.e("find location error", response.errorBody().toString());
                    Toast.makeText(MainPage.this, "매장 불러오기 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                locationList = response.body();
                for(Location l : locationList){
                    drawLocation(Color.WHITE, l);
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e("find location fail", t.getMessage());
                Toast.makeText(MainPage.this, "매장 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setToken(){
        token = sharedPreferenceUtil.getData("token", "err");
        tokenInterceptor = new TokenInterceptor();
        tokenInterceptor.setToken(token);
    }

    private void addLocation(){
        findViewById(R.id.mainPage_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                textView.setText("시작점 선택");
            }
        });
    }
    private void moveLocation(Location location, MotionEvent event){
        isMove = true;
        drawLocation(Color.BLACK, location);

        Log.e("eventX", event.getX() + "  " + preX);
        int dx = (int) (event.getX() - preX);
        int dy = (int) (event.getY() - preY);
        preX = event.getX();
        preY = event.getY();
        location.setStartX(location.getStartX() + dx);
        location.setStartY(location.getStartY() + dy);
        location.setEndX(location.getEndX() + dx);
        location.setEndY(location.getEndY() + dy);

        drawLocation(Color.YELLOW, location);
    }

    private void removeLocation(){
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("삭제할 매대를 선택하세요");
                isRemove = true;
            }
        });
    }

    private boolean isClickLocation(float x, float y){
        for(Location location : locationList){
            float locationSX = location.getStartX();
            float locationSY = location.getStartY();
            float locationEX = location.getEndX();
            float locationEY = location.getEndY();

            if((x >= locationSX && x <= locationEX)
                    && (y >= locationSY && y <= locationEY)){
                Log.e("test", "안에 체크 됨");
                moveLocation = location;
                isIn = true;
                return true;
            }
        }
        return false;
    }

    private void drawLocation(int color, Location location){
        paint.setColor(color);
        canvas.drawRect(location.getStartX(), location.getStartY(), location.getEndX(), location.getEndY(), paint);
        frame.invalidate();
    }

    private void registItem(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("물건 추가를 할 매대를 선택하세요");
                isRegister = true;
            }
        });
    }
    private void addItem(){
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
    
    private void registLocation(){
        nowStore.setLocation(locationList);
        Log.e("loc size", locationList.size() + "");
        Call<List<Location>> registStore = service.registStoreLocation(nowStore);
        registStore.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(!response.isSuccessful()){
                    Log.e("regist Location Error", response.errorBody().toString());
                    Toast.makeText(MainPage.this, "매장 수정 에러", Toast.LENGTH_SHORT).show();
                    return;
                }
                setNowStoreLocation(response.body());
                Toast.makeText(MainPage.this, "매장 수정 성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e("regist location fail", t.getMessage());
                Toast.makeText(MainPage.this, "매장 수정 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void setNowStoreLocation(List<Location> locations){
        nowStore.setLocation(locations);
        sharedPreferenceUtil.remove("store");
        sharedPreferenceUtil.putData("store", gson.toJson(nowStore, Store.class));
    }

    private void setLocationData(Location location){
        locationList.remove(location);
        locationList.add(location);
    }
}
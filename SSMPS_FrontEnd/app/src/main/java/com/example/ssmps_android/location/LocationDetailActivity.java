package com.example.ssmps_android.location;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ssmps_android.R;
import com.example.ssmps_android.Recyclerview.CustomAdapter3;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LocationDetailActivity extends AppCompatActivity {
    EditText searchInput;

    Store nowStore;

    SharedPreferenceUtil sharedPreferenceUtil;
    Gson gson;

    List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initData();

        findViewById(R.id.locationDetail_search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i =0; i<20; i++) {
            testDataSet.add("DATA" + i);
        }
        RecyclerView recyclerView = findViewById(R.id.locationDetail_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter3 customAdapter3 = new CustomAdapter3(testDataSet);
        recyclerView.setAdapter(customAdapter3);
    }

    private void initData(){
        sharedPreferenceUtil = new SharedPreferenceUtil(getApplicationContext());
        gson = new GsonBuilder().create();

        nowStore = gson.fromJson(sharedPreferenceUtil.getData("store", "err"), Store.class);
    }
    private void setItemList(){

    }
}
package com.example.ssmps_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_select);

        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i =0; i<20; i++) {
            testDataSet.add("TEST DATA" + i);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapterActivity customAdapter = new CustomAdapterActivity(testDataSet);
        recyclerView.setAdapter(customAdapter);
    }
}

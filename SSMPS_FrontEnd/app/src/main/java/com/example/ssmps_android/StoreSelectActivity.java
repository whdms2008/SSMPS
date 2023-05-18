package com.example.ssmps_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.domain.Location;
import com.example.ssmps_android.domain.Store;

import java.util.ArrayList;
import java.util.Locale;

public class StoreSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_select);

        ArrayList<Store> testDataSet = new ArrayList<>();
        for (int i =0; i<20; i++) {
            testDataSet.add(new Store(Long.valueOf(i), i + "번 매장", "천안", null));
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(testDataSet);
        recyclerView.setAdapter(customAdapter);
    }
}

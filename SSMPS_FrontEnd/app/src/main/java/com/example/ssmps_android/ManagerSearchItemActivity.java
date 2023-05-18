package com.example.ssmps_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Store;

import java.util.ArrayList;

public class ManagerSearchItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_manager);

        ArrayList<Item> testDataSet = new ArrayList<>();
        for (int i =0; i<20; i++) {
            testDataSet.add(new Item(Long.valueOf(i), null, "테스트", null, 1000, 1000,null,null,null));
        }
        RecyclerView recyclerView = findViewById(R.id.itemSearchManager_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter2 customAdapter2 = new CustomAdapter2(testDataSet);
        recyclerView.setAdapter(customAdapter2);
    }
}

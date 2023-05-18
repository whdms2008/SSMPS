package com.example.ssmps_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.domain.Item;

import java.util.ArrayList;

public class ManagerSearchItemActivity extends AppCompatActivity {
    EditText itemNameInput;
    Button searchBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_manager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();
        searchItem();

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
    
    private void initData(){
        itemNameInput = findViewById(R.id.managerSearchItem_item_name_input);
        searchBtn = findViewById(R.id.managerSearchItem_search_btn);
    }
    
    private void searchItem(){
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에서 데이터 가져오기
                String itemName = itemNameInput.getText().toString();
                
            }
        });
    }
}
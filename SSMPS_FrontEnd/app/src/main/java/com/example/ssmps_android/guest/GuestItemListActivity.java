package com.example.ssmps_android.guest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ssmps_android.R;
import com.example.ssmps_android.Recyclerview.CustomAdapter4;
import com.example.ssmps_android.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class GuestItemListActivity extends AppCompatActivity {
    TextView itemName;
    List<Item> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_item_list);

        initData();
    }
    private void setRecyclerview(){
        RecyclerView recyclerView = findViewById(R.id.guestItemList_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter4 customAdapter4 = new CustomAdapter4(itemList);
        recyclerView.setAdapter(customAdapter4);
    }

    private void initData(){
        itemName = findViewById(R.id.guestItemList_name);

//        int locationId = 7;
//        Intent intent = new Intent();
//        intent.putExtra("location", locationId);
//        setResult(Activity.RESULT_OK, intent);
//        finish();
    }
}


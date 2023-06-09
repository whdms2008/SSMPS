package com.example.ssmps_android.guest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssmps_android.R;
import com.example.ssmps_android.domain.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GuestItemInfoActivity extends AppCompatActivity {
    TextView itemName, itemPrice, itemType, itemQuantity;
    ImageView itemImg;
    Gson gson;
    Item nowItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_item_info);
        initData();
        setItemData();
    }

    private void initData(){
        itemName = findViewById(R.id.guestItemInfo_item_name);
        itemPrice = findViewById(R.id.guestItemInfo_item_price);
        itemType = findViewById(R.id.guestItemInfo_item_type);
        itemQuantity = findViewById(R.id.guestItemInfo_item_quantity);
        itemImg = findViewById(R.id.guestItemInfo_item_img);

        gson = new GsonBuilder().create();
        Intent intent = getIntent();
        nowItem = (Item) intent.getSerializableExtra("item");
    }

    private void setItemData(){
        itemName.setText(nowItem.getName());
        itemPrice.setText(Integer.toString(nowItem.getPrice()));
        itemType.setText(nowItem.getType());
        itemQuantity.setText(Integer.toString(nowItem.getQuantity()));
    }


}

package com.example.ssmps_android.guest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.ssmps_android.R;

public class GuestItemSearchActivity extends AppCompatActivity {
    EditText itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_item_list);

        initData();
    }

    private void initData(){
        itemName = findViewById(R.id.guestItemSearch_item_name);
    }
}
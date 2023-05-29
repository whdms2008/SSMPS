package com.example.ssmps_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ssmps_android.domain.Item;

public class GuestSearchItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_search_item);


        findViewById(R.id.guest_search_location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int locationId = 7;
                Intent intent = new Intent();
                intent.putExtra("location", locationId);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
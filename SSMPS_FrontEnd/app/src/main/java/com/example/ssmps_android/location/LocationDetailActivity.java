package com.example.ssmps_android.location;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ssmps_android.R;

public class LocationDetailActivity extends AppCompatActivity {
    EditText searchInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.locationDetail_search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
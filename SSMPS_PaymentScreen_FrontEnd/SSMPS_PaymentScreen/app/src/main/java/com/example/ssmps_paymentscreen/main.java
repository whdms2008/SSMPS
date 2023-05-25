package com.example.ssmps_paymentscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class main extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button startButton = findViewById(R.id.payment_button);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(main.this, "결제되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), start.class);
                startActivity(intent);
            }
        });

        ListViewAdapter adapter = new ListViewAdapter();
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        adapter.addItem("포카리");
        adapter.addItem("코카콜라");
        adapter.addItem("빼빼로");
        adapter.notifyDataSetChanged();
    }
}

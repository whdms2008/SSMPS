package com.example.ssmps_paymentscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<ListViewItem> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button startButton = findViewById(R.id.payment_button);
        startButton.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "결제되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), start.class);
            startActivity(intent);
        });

        items = new ArrayList<>();

        items.add(new ListViewItem("포카리", 2000, 2));
        items.add(new ListViewItem("코카콜라", 3000, 3));
        items.add(new ListViewItem("빼빼로", 4000, 3));
        items.add(new ListViewItem("포카리", 2000, 2));
        items.add(new ListViewItem("빼빼로", 4000, 3));
        items.add(new ListViewItem("포카리", 2000, 2));
        items.add(new ListViewItem("코카콜라", 3000, 3));
        items.add(new ListViewItem("빼빼로", 4000, 3));
        items.add(new ListViewItem("포카리", 2000, 2));
        items.add(new ListViewItem("코카콜라", 3000, 3));
        items.add(new ListViewItem("빼빼로", 4000, 3));

        ListViewAdapter adapter = new ListViewAdapter(this, items);
        ListView listView = findViewById(R.id.product_list);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }
    public void updateAllPrice(){
        TextView sum_price = findViewById(R.id.sum_price);
        int s_price = 0;
        for(int i = 0; i < items.size(); i++){
            s_price = s_price + Integer.parseInt(items.get(i).getAll_price());
        }
        sum_price.setText("총 금액: " + String.valueOf(s_price));
    }
}

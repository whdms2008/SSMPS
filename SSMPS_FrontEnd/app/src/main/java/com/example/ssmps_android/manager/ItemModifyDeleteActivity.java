package com.example.ssmps_android.manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ssmps_android.R;

public class ItemModifyDeleteActivity extends AppCompatActivity {
    EditText itemQuantity;
    TextView itemName, itemType, itemPrice;
    ImageView itemImg;
    Button modifyBtn, deleteBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_delete_modify);

        initData();
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initData(){
        itemName = findViewById(R.id.itemDeleteModify_item_name);
        itemQuantity = findViewById(R.id.itemDeleteModify_item_quantity);
        itemType = findViewById(R.id.itemDeleteModify_item_type);
        itemPrice = findViewById(R.id.itemDeleteModify_item_price);

        itemImg = findViewById(R.id.itemDeleteModify_item_img);
        modifyBtn = findViewById(R.id.itemDeleteModify_modify_btn);
        deleteBtn = findViewById(R.id.itemDeleteModify_delete_btn);
    }

    private void setItemData(){

    }
}

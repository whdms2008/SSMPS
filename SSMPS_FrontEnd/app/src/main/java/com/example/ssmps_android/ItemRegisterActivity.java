package com.example.ssmps_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ItemRegisterActivity extends AppCompatActivity{
    EditText itemNameInput, itemName, itemQuantity, itemType;
    ImageView itemImage;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initData();
        searchItem();

    }

    private void initData(){
        itemName = findViewById(R.id.itemRegister_item_name);
        itemQuantity = findViewById(R.id.itemRegister_quantity);
        itemType = findViewById(R.id.itemRegister_quantity);

        itemImage = findViewById(R.id.itemRegister_item_img);
    }

    private void searchItem(){
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = itemNameInput.getText().toString();
//                if(name.isEmpty()){
//                    Toast.makeText(ItemRegisterActivity.this, "검색어를 입력하세요", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                //DB에서 데이터 가져오기
//
//            }
//        });
    }
}

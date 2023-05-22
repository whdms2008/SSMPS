package com.example.ssmps_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssmps_android.domain.CenterItem;

public class ItemRegisterActivity extends AppCompatActivity{
    EditText itemName, itemQuantity, itemType;
    ImageView itemImage;
    CenterItem nowItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initData();
        setItemData();

    }

    private void initData(){
        itemName = findViewById(R.id.itemRegister_item_name);
        itemQuantity = findViewById(R.id.itemRegister_quantity);
        itemType = findViewById(R.id.itemRegister_quantity);

        itemImage = findViewById(R.id.itemRegister_item_img);
    }

    private void setItemData(){
        Intent intent = getIntent();
        nowItem = (CenterItem) intent.getSerializableExtra("item");
        itemName.setText(nowItem.getName());
        itemType.setText(nowItem.getType());
//        itemImage.setImageBitmap();
        // 이미지 blob -> Bitmap로 바꿔서 등록
    }

}

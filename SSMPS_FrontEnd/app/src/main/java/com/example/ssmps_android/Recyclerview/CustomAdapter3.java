package com.example.ssmps_android.Recyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ssmps_android.R;
import com.example.ssmps_android.data.SharedPreferenceUtil;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.domain.Location;
import com.example.ssmps_android.network.RetrofitAPI;
import com.example.ssmps_android.network.RetrofitClient;
import com.example.ssmps_android.network.TokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.ViewHolder> {

    private List<Item> localDataSet = new ArrayList<>();
    private List<Item> locationItemList = new ArrayList<>();
    private Location nowLocation;
    Context context;

//    Retrofit retrofit;
//    RetrofitAPI service;
//    TokenInterceptor tokenInterceptor;
//    SharedPreferenceUtil sharedPreferenceUtil;
//    Gson gson;
//    String token;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ImageView itemImg;
        private CheckBox itemChcek;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.locationDetail_item_name);
            itemImg = itemView.findViewById(R.id.locationDetail_item_img);
            itemChcek = itemView.findViewById(R.id.locationDetail_item_check);
        }
        public TextView getTextView() {
            return itemName;
        }
    }

    public CustomAdapter3 (List<Item> dataSet, Location nowLocation) {
        localDataSet = dataSet;
        this.nowLocation = nowLocation;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_locationdetail, parent, false);
        CustomAdapter3.ViewHolder viewHolder = new CustomAdapter3.ViewHolder(view);
        context = parent.getContext();



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = localDataSet.get(position);
        holder.itemName.setText(item.getName());
        List<Item> itemList = nowLocation.getItemList();
        Log.e("hhhh", nowLocation.getId() + "");
        Log.e("itemList", itemList.size() + "");
        for(Item i : itemList){
            if(i.getName().equals(item.getName())){
                holder.itemChcek.setChecked(true);
                locationItemList.add(i);
            }
        }

        holder.itemChcek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    locationItemList.add(item);
                }else {
                    locationItemList.remove(locationItemList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public List<Item> getLocationItemList(){
        return locationItemList;
    }

}

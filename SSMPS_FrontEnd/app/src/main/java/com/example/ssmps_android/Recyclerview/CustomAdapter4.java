package com.example.ssmps_android.Recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.R;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.guest.GuestItemListActivity;
import com.example.ssmps_android.guest.StoreViewActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter4 extends RecyclerView.Adapter<CustomAdapter4.ViewHolder> {

    private List<Item> localDataSet = new ArrayList<>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Button locationBtn, infoBtn;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            locationBtn = itemView.findViewById(R.id.guest_item_location_btn);
            infoBtn = itemView.findViewById(R.id.guest_item_information_btn);
            image = itemView.findViewById(R.id.guest_item_image);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public CustomAdapter4(List<Item> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_itemlistguest, parent, false);
        CustomAdapter4.ViewHolder viewHolder = new CustomAdapter4.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = localDataSet.get(position);

        holder.textView.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent2(context, GuestItemListActivity.class);
//                intent.putExtra("item", item);
//                context.startActivity(intent);
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });

        holder.locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreViewActivity.class);
//                Intent intent = ((Activity) context).getIntent();
                intent.putExtra("item", item);
                Log.e("item", item.getName());
                ((Activity) context).setResult(Activity.RESULT_OK, intent);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
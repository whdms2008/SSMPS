package com.example.ssmps_android.Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssmps_android.ItemRegisterActivity;
import com.example.ssmps_android.R;
import com.example.ssmps_android.domain.CenterItem;
import com.example.ssmps_android.domain.Item;
import com.example.ssmps_android.dto.CenterItemResponse;
import com.example.ssmps_android.manager.ItemModifyDeleteActivity;

import java.util.ArrayList;
import java.util.List;

public class ManagerModifyDeleteAdapter extends RecyclerView.Adapter<ManagerModifyDeleteAdapter.ViewHolder> {
    private List<Item> itemList = new ArrayList<>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public ManagerModifyDeleteAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

//    public CustomAdapter2(List<Item> dataSet){
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_itemsearchmanager, parent, false);
        ManagerModifyDeleteAdapter.ViewHolder viewHolder = new ManagerModifyDeleteAdapter.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.textView.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemModifyDeleteActivity.class);
                intent.putExtra("item", item);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

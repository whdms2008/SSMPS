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

import java.util.ArrayList;
import java.util.List;

public class ManagerRegistSearchAdapter extends RecyclerView.Adapter<ManagerRegistSearchAdapter.ViewHolder> {

    private List<CenterItem> itemList = new ArrayList<>();
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

    public ManagerRegistSearchAdapter(List<CenterItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ManagerRegistSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_itemsearchmanager, parent, false);
        ManagerRegistSearchAdapter.ViewHolder viewHolder = new ManagerRegistSearchAdapter.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerRegistSearchAdapter.ViewHolder holder, int position) {
        CenterItem item = itemList.get(position);

        holder.textView.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemRegisterActivity.class);
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

package com.example.ssmps_paymentscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ListViewItem> {
    public ListViewAdapter(Context context, List<ListViewItem> items) {
        super(context, R.layout.list_item, items);
        this.listviewItemList.addAll(items);
    }


    private final ArrayList<ListViewItem> listviewItemList = new ArrayList<>();


    @Override
    public int getCount() {
        return listviewItemList.size();
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ListViewItem item = getItem(position);

        TextView text1 = convertView.findViewById(R.id.product_name);
        TextView text2 = convertView.findViewById(R.id.price);
        TextView text3 = convertView.findViewById(R.id.cnt);

        if (item != null) {
            text1.setText(item.getText());
            text2.setText(item.getPrice());
            text3.setText(item.getCnt());
        }

        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ListViewItem getItem(int position) {
        return listviewItemList.get(position);
    }

    public void addItem(String text, int price, int cnt) {
        ListViewItem item = new ListViewItem(text, price, cnt);
        listviewItemList.add(item);
    }

}

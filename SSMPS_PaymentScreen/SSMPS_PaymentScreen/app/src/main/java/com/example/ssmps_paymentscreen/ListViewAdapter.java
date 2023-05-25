package com.example.ssmps_paymentscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private TextView text;

    private ArrayList<ListViewItem> listviewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){}

    @Override
    public int getCount() {
        return listviewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        text = (TextView) convertView.findViewById(R.id.text);

        ListViewItem listViewItem = listviewItemList.get(position);

        text.setText(listViewItem.getText());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listviewItemList.get(position);
    }

    public void addItem(String text){
        ListViewItem item = new ListViewItem();

        item.setText(text);
        listviewItemList.add(item);
    }
}

package com.app.tourguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.tourguide.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<TourItem> itemList;

    public ItemAdapter(Context context, List<TourItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tour_list_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        TextView description = convertView.findViewById(R.id.description);

        TourItem item = itemList.get(position);
        title.setText(item.getTitle());
        description.setText(item.getDescription());

        return convertView;
    }
}

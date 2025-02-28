package com.app.tourguide.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.tourguide.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide; // For loading flag images
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
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tour_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TourItem item = itemList.get(position);

        // Set text data
        holder.title.setText(item.getTitle());
        holder.region.setText("Region: " + item.getRegion());
        holder.season.setText("Season: " + item.getSeason());
        holder.duration.setText("Duration: " + item.getDuration());
        holder.description.setText(item.getDescription());
        holder.country.setText("Country: " + item.getCountry());

        // 🔍 Debugging: Check the value of `item.getSchedule()`
        Log.d("ItemAdapter", "Raw schedule data: " + item.getSchedule());

        // Ensure schedule is properly retrieved and formatted
        List<String> scheduleList = item.getSchedule();
        if (scheduleList == null || scheduleList.isEmpty()) {
            holder.schedule.setText("Schedule:\n• No schedule available");
        } else {
            String scheduleText = "Schedule:\n• " + String.join("\n• ", scheduleList);
            holder.schedule.setText(scheduleText);
        }

        // 🔍 Debugging: Check the final formatted schedule string
        Log.d("ItemAdapter", "Formatted schedule text: " + holder.schedule.getText());

        // Load flag image using Glide (ensure you add Glide dependency)
        Glide.with(context)
                .load(item.getFlag()) // URL or Drawable resource name
                .placeholder(R.drawable.placeholder_flag) // Default image in case of failure
                .into(holder.flag);

        return convertView;
    }

    private static class ViewHolder {
        TextView title, region, season, duration, description, country, schedule;
        ImageView flag;

        ViewHolder(View view) {
            title = view.findViewById(R.id.title);
            region = view.findViewById(R.id.region);
            season = view.findViewById(R.id.season);
            duration = view.findViewById(R.id.duration);
            description = view.findViewById(R.id.description);
            country = view.findViewById(R.id.country);
            schedule = view.findViewById(R.id.schedule);
            flag = view.findViewById(R.id.flag);
        }
    }
}


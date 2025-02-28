package com.app.tourguide.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {
    private Context context;
    private DatabaseHelper dbHelper;

    public DataImporter(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public void importDataFromJson() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction(); // Start transaction to optimize bulk insert

        try {
            InputStream is = context.getAssets().open("tour_guide_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray toursArray = jsonObject.getJSONArray("tours");

            for (int i = 0; i < toursArray.length(); i++) {
                JSONObject tour = toursArray.getJSONObject(i);
                ContentValues values = new ContentValues();

                // ❌ Don't manually insert `_id` (let SQLite autogenerate it)
                // if (tour.has("id")) { values.put(DatabaseHelper.COLUMN_ID, tour.getInt("id")); }

                values.put(DatabaseHelper.COLUMN_COUNTRY, tour.optString("country", "Unknown"));
                values.put(DatabaseHelper.COLUMN_FLAG, tour.optString("flag", "@drawable/placeholder_flag"));

                // Convert schedule array to a comma-separated string
                String scheduleText = "No schedule available";
                if (tour.has("schedule")) {
                    JSONArray scheduleArray = tour.getJSONArray("schedule");
                    List<String> scheduleList = new ArrayList<>();
                    for (int j = 0; j < scheduleArray.length(); j++) {
                        scheduleList.add(scheduleArray.getString(j));
                    }
                    scheduleText = TextUtils.join(", ", scheduleList);
                }
                values.put(DatabaseHelper.COLUMN_SCHEDULE, scheduleText);

                // Existing columns
                values.put(DatabaseHelper.COLUMN_TITLE, tour.optString("title", "No Title"));
                values.put(DatabaseHelper.COLUMN_CATEGORY, tour.has("category") ? tour.getJSONArray("category").toString() : "[]");
                values.put(DatabaseHelper.COLUMN_REGION, tour.optString("region", "Unknown"));
                values.put(DatabaseHelper.COLUMN_THEME, tour.has("theme") ? tour.getJSONArray("theme").toString() : "[]");
                values.put(DatabaseHelper.COLUMN_SEASON, tour.optString("season", "Unknown"));
                values.put(DatabaseHelper.COLUMN_DURATION, tour.optString("duration", "Unknown"));
                values.put(DatabaseHelper.COLUMN_DESCRIPTION, tour.optString("description", "No Description"));

                // Attempt to insert into database
                long result = db.insert(DatabaseHelper.TABLE_TOURS, null, values);
                if (result == -1) {
                    Log.e("Database Insert", "Failed to insert item at index " + i);
                } else {
                    Log.d("Database Insert", "Successfully inserted item at index " + i);
                }
            }

            db.setTransactionSuccessful(); // Commit transaction
        } catch (Exception e) {
            Log.e("DataImport", "Error importing data: ", e);
        } finally {
            db.endTransaction(); // End transaction
            db.close();
        }
    }



}

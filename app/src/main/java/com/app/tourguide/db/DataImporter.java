package com.app.tourguide.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;

public class DataImporter {
    private Context context;
    private DatabaseHelper dbHelper;

    public DataImporter(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public void importDataFromJson() {
        try {
            InputStream is = context.getAssets().open("tour_guide_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray toursArray = jsonObject.getJSONArray("tours");

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            for (int i = 0; i < toursArray.length(); i++) {
                JSONObject tour = toursArray.getJSONObject(i);
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_TITLE, tour.getString("title"));
                values.put(DatabaseHelper.COLUMN_CATEGORY, tour.getJSONArray("category").toString());
                values.put(DatabaseHelper.COLUMN_REGION, tour.getString("region"));
                values.put(DatabaseHelper.COLUMN_THEME, tour.getJSONArray("theme").toString());
                values.put(DatabaseHelper.COLUMN_SEASON, tour.getString("season"));
                values.put(DatabaseHelper.COLUMN_DURATION, tour.getString("duration"));
                values.put(DatabaseHelper.COLUMN_DESCRIPTION, tour.getString("description"));

                db.insert(DatabaseHelper.TABLE_TOURS, null, values);
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

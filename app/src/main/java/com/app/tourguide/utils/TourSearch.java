package com.app.tourguide.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.tourguide.db.DatabaseHelper;
import com.app.tourguide.enums.Category5Duration;
import com.app.tourguide.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourSearch {
    private DatabaseHelper dbHelper;

    // Constructor to initialize DatabaseHelper
    public TourSearch(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public List<Tour> searchTours(String category1type, String category2theme,
                                  String category3region, String category4season,
                                  String category5duration) {
        List<Tour> filteredTours = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_TOURS + " WHERE 1=1";
        List<String> selectionArgs = new ArrayList<>();

        if (category1type != null) {
            query += " AND " + DatabaseHelper.COLUMN_CATEGORY + " LIKE ?";
            selectionArgs.add("%" + category1type + "%");
        }
        if (category2theme != null) {
            query += " AND " + DatabaseHelper.COLUMN_THEME + " LIKE ?";
            selectionArgs.add("%" + category2theme + "%");
        }
        if (category3region != null) {
            query += " AND " + DatabaseHelper.COLUMN_REGION + " = ?";
            selectionArgs.add(category3region);
        }
        if (category4season != null) {
            query += " AND " + DatabaseHelper.COLUMN_SEASON + " = ?";
            selectionArgs.add(category4season);
        }
        if (category5duration != null) {
            String durationValue = getDurationString(category5duration);
            query += " AND " + DatabaseHelper.COLUMN_DURATION + " = ?";
            selectionArgs.add(durationValue);
        }

        query += " LIMIT 3"; // Limit to 3 results

        Cursor cursor = db.rawQuery(query, selectionArgs.toArray(new String[0]));

        if (cursor.moveToFirst()) {
            do {
                Tour tour = new Tour(
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REGION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SEASON)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DURATION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION))
                );
                filteredTours.add(tour);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return filteredTours;
    }

    // Helper method to convert Category5Duration to database format
    private String getDurationString(String duration) {
        switch (duration) {
            case "THREEDAYS":
                return "3 days";
            case "FIVEDAYS":
                return "5 days";
            case "SEVENDAYS":
                return "7 days";
            default:
                return "";
        }
    }
}


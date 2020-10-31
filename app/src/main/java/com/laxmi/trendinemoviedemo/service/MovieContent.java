package com.laxmi.trendinemoviedemo.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MovieContent {

    /**
     * An array of sample (dummy) items.
     */
    public   static final ArrayList<Movie> ITEMS = new ArrayList<Movie>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public   static final Map<String, Movie> ITEM_MAP = new HashMap<String, Movie>();



    private static void addItem(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setData(List<Movie> data){

        data.forEach((n) -> addItem(n));

    }



    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


}
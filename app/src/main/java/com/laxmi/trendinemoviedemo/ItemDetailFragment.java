package com.laxmi.trendinemoviedemo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laxmi.trendinemoviedemo.service.Movie;
import com.laxmi.trendinemoviedemo.service.MovieContent;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";


    private Movie mItem;


    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            mItem = MovieContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            AppBarLayout appBarLayout = (AppBarLayout)activity.findViewById(R.id.app_bar);
            TextView title = appBarLayout.findViewById(R.id.movie_title);
            title.setText(mItem.getTitle());

            LinearLayout rootView  = (LinearLayout)activity.findViewById(R.id.movie_detail_container);

            System.out.println(" rootView children "+rootView.getChildCount());
            rootView.getChildAt(2);
            rootView.getChildAt(3);
            rootView.getChildAt(4);

            if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.text_movie_detail)).setText(mItem.getOverview());

                ((TextView)  rootView.getChildAt(1)).setText("Language: "+mItem.getOriginal_language()+"");
                ((TextView)  rootView.getChildAt(2)).setText("Votes Count : "+mItem.getVote_count());
                ((TextView)  rootView.getChildAt(3)).setText("Release Date: "+mItem.getRelease_date());
                ((TextView)  rootView.getChildAt(4)).setText("Voters: "+mItem.getVote_average()+"");
                ((TextView)  rootView.getChildAt(5)).setText("Media Type : "+mItem.getMedia_type());
                ((TextView)  rootView.getChildAt(6)).setText("Original Title: "+mItem.getOriginal_title());
                ((TextView)  rootView.getChildAt(7)).setText("Popularity: "+mItem.getPopularity()+"");


        }


        }
    }

}
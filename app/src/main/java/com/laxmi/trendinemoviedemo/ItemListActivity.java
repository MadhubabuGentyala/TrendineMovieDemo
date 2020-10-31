package com.laxmi.trendinemoviedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laxmi.trendinemoviedemo.service.Api;
import com.laxmi.trendinemoviedemo.service.MovieContent;
import com.laxmi.trendinemoviedemo.service.Movie;
import com.laxmi.trendinemoviedemo.service.MovieList;
import com.laxmi.trendinemoviedemo.service.RetrofitClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private String mUrlString = "https://api.themoviedb.org/3/trending/movie/week?page=1&api_key=54f3fb955ac98e731fa7efc6f1c0c0d6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {

            mTwoPane = false;
        }

        getMovies(1);

    }

    private void getMovies(int pageNo) {
        System.out.println(" get movies called ");
        Call<MovieList> call = RetrofitClient.getInstance().getMyApi().getTrendingMovies(Api.API_KEY);



        call.enqueue(new Callback<MovieList>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList heroList = response.body();

                View recyclerView = findViewById(R.id.item_list);
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView,heroList.getMovies());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                System.out.println(" failure response message "+t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Movie> data) {

        setData(data);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this,data, mTwoPane));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setData(List<Movie> data) {

        MovieContent.setData(data);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<Movie> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie item = (Movie) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<Movie> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            URL newurl = null;
            holder.mTileView.setText(mValues.get(position).getTitle());

            holder.mIdView.setText("");
            holder.mContentView.setText(mValues.get(position).getOverview());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;
            final TextView mTileView;
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.catch_image);
                mTileView = (TextView) view.findViewById(R.id.movie_title);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }


}
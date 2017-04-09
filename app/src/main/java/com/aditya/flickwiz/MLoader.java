package com.aditya.flickwiz;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Aditya on 27/03/17.
 */

public class MLoader extends AsyncTaskLoader<List<Movie>> {

    public static final String LOG_TAG = MLoader.class.getName();

    private String mUrl;

    public MLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "This is onStartLoading.....");
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        Log.i(LOG_TAG, "This is loadInBackground.....");

        if(mUrl==null) {
            return null;
        }

        List<Movie> movies = QueryUtils.fetchMovieData(mUrl);
        return movies;
    }
}


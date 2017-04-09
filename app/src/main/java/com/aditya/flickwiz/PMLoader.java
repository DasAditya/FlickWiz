package com.aditya.flickwiz;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Aditya on 27/03/17.
 */

public class PMLoader extends AsyncTaskLoader<List<Details>> {

    public static final String LOG_TAG = MLoader.class.getName();

    private String mUrl;

    public PMLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "This is onStartLoading.....");
        forceLoad();
    }

    @Override
    public List<Details> loadInBackground() {
        Log.i(LOG_TAG, "This is loadInBackground.....");

        if(mUrl==null) {
            return null;
        }

        List<Details> details = PQueryUtils.fetchEarthQuakeData(mUrl);
        return details;
    }
}

package com.aditya.flickwiz;

/**
 * Created by Aditya on 25/03/17.
 */

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import static android.view.View.GONE;

public class Fetch extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Details>> {

    String movie_title, wurl, yurl;

    public static final String LOG_TAG = Fetch.class.getName();

    private static final String USGS_REQUEST_URL = "http://www.omdbapi.com/?";


    @Override
    public Loader<List<Details>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "This is onCreateLoader.....");

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uribuilder = baseUri.buildUpon();
        Intent intent = getIntent();
        movie_title = intent.getStringExtra("movie_title");
        wurl = intent.getStringExtra("wurl");
        yurl = intent.getStringExtra("yurl");

        uribuilder.appendQueryParameter("t", movie_title);

        Toast.makeText(Fetch.this, uribuilder.toString(), Toast.LENGTH_LONG).show();
        return new PMLoader(this, uribuilder.toString());

    }




    @Override
    public void onLoadFinished(Loader<List<Details>> loader, List<Details> earthquakes) {

       View loading_bar = findViewById(R.id.loading_spinner);
        loading_bar.setVisibility(GONE);

        Log.i(LOG_TAG, "This is onLoadFinished.....");
        if(earthquakes != null && !earthquakes.isEmpty()) {
            Log.i(LOG_TAG, earthquakes.toString());



            Details details = earthquakes.get(0);

            Log.d("############","Items " +  details.getGenre() );
            Intent PMovieIntent = new Intent(Fetch.this, PMovieActivity.class);
            PMovieIntent.putExtra("genre",details.getGenre());
            PMovieIntent.putExtra("actor",details.getActor());
            PMovieIntent.putExtra("director",details.getDirector());
            PMovieIntent.putExtra("plot",details.getPlot());
            PMovieIntent.putExtra("poster",details.getPoster());
            PMovieIntent.putExtra("released",details.getReleased());
            PMovieIntent.putExtra("runtime",details.getRuntime());
            PMovieIntent.putExtra("writer",details.getWriter());
            PMovieIntent.putExtra("movie_title",movie_title);
            PMovieIntent.putExtra("wurl",wurl);
            PMovieIntent.putExtra("yurl",yurl);
            PMovieIntent.putExtra("rating",details.getRating());
            PMovieIntent.putExtra("rating1", details.getRating1());


            // Start the new activity
            startActivity(PMovieIntent);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Details>> loader) {
        Log.i(LOG_TAG, "This is onLoadReset.....");
        //adapter.clear();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmovie);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        }
 else {
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

        }


        }


}


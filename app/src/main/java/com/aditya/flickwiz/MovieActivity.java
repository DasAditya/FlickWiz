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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {


    public static final String LOG_TAG = MovieActivity.class.getName();

    private static final String USGS_REQUEST_URL = "https://www.tastekid.com/api/similar";
    public MovieAdapter adapter;

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "This is onCreateLoader.....");

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uribuilder = baseUri.buildUpon();
        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("mname");
        uribuilder.appendQueryParameter("k", "264606-AdityaDa-MW7A1QQ0");
        uribuilder.appendQueryParameter("verbose", "1");
        uribuilder.appendQueryParameter("q", movie_title);
        uribuilder.appendQueryParameter("type", "movie");
        return new MLoader(this, uribuilder.toString());

    }




    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {

       View loading_bar = findViewById(R.id.loading_spinner);
        loading_bar.setVisibility(GONE);
        Log.i(LOG_TAG, "This is onLoadFinished.....");
        if(movies != null && !movies.isEmpty()) {
            Log.i(LOG_TAG, movies.toString());

            adapter.addAll(movies);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        Log.i(LOG_TAG, "This is onLoadReset.....");
        adapter.clear();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);

        final ArrayList<Movie> movies = new ArrayList<Movie>();

        adapter = new MovieAdapter(this, movies);


        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link MovieAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Movie} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                                    long id) {

                Movie movie = movies.get(pos);
                // TODO Auto-generated method stub
                Log.d("############","Items " +  movie.getTitle() );
                Intent PMovieIntent = new Intent(MovieActivity.this, Fetch.class);
                PMovieIntent.putExtra("movie_title", movie.getTitle());
                PMovieIntent.putExtra("wurl", movie.getWurl());
                PMovieIntent.putExtra("yurl", movie.getYurl());
                PMovieIntent.putExtra("synopsis", movie.getSynopsis());

                // Start the new activity
                startActivity(PMovieIntent);
            }

        });



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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent movieIntent = new Intent(MovieActivity.this, MainActivity.class);
        startActivity(movieIntent);
    }


}


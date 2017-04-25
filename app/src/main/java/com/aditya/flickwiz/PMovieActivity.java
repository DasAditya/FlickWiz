package com.aditya.flickwiz;

/**
 * Created by Aditya on 25/03/17.
 */

import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.view.View.GONE;
import static com.aditya.flickwiz.R.id.poster;

public class PMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    String movie_title;
    String y[];

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmovie);
        final Switch makefavo = (Switch) findViewById(R.id.makefav);

        Intent intent = getIntent();
        movie_title = intent.getStringExtra("movie_title");
        final String wurl = intent.getStringExtra("wurl");
        final String yurl = intent.getStringExtra("yurl");
        final String synopsis = intent.getStringExtra("plot");
        final String genre = intent.getStringExtra("genre");
        final String director = intent.getStringExtra("director");
        final String actor = intent.getStringExtra("actor");
        final String writer = intent.getStringExtra("writer");
        final String poster = intent.getStringExtra("poster");
        final String runtime = intent.getStringExtra("runtime");
        final String released = intent.getStringExtra("released");
        final String rating = intent.getStringExtra("rating");
        final String rating1 = intent.getStringExtra("rating1");
        // Create a list of words
        // View loading_bar = findViewById(R.id.loading_spinner);
        //loading_bar.setVisibility(GONE);
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(movie_title);
        TextView wurlview = (TextView) findViewById(R.id.wurl);
        wurlview.setText(wurl);
        TextView yurlview = (TextView) findViewById(R.id.yurl);
        yurlview.setText(yurl);
        TextView synopsisview = (TextView) findViewById(R.id.description);
        synopsisview.setText("Description : " + synopsis);
        String r[] ;
        r = released.split(" ");
        TextView yearview = (TextView) findViewById(R.id.year);
        if(r.length>1)
        yearview.setText("(" + r[2] + ")");
        else
            yearview.setText("(" + "2014" + ")");

        TextView genreview = (TextView) findViewById(R.id.genre);
        genreview.setText("Genre : " + genre);

        TextView directorview = (TextView) findViewById(R.id.director);
        directorview.setText(director);

        TextView actorview = (TextView) findViewById(R.id.actor);
        actorview.setText(actor);
        TextView writerview = (TextView) findViewById(R.id.writer);
        writerview.setText(writer);
        TextView runtimeview = (TextView) findViewById(R.id.runtime);
        runtimeview.setText("Runtime : " + runtime);
        TextView imdbrating = (TextView) findViewById(R.id.imdb);
        TextView rtrating = (TextView) findViewById(R.id.rt);
        imdbrating.setText(rating);
        rtrating.setText(rating1);


//        WebView pview = (WebView) findViewById(R.id.poster);
//        pview.loadUrl(poster);
        ImageView pview = (ImageView) findViewById(R.id.poster);
        Picasso.with(getApplicationContext()).load(poster).into(pview);


//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    InputStream in = new URL(poster).openStream();
//                    bmp = BitmapFactory.decodeStream(in);
//                } catch (Exception e) {
//                    Log.d("@#@#",poster);
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result) {
//                if (bmp != null)
//                    pview.setImageBitmap(bmp);
//            }
//
//        }.execute();
        List<Fav> favs = db.getAllFavs();

        for (Fav cn : favs) {
            Log.d("&*####", cn.getName());
            if (cn.getName().equals(movie_title)) {
                Log.d("***", "same");
                makefavo.setChecked(true);
            }
        }

        makefavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check current state of a Switch (true or false).
                Boolean switchState = makefavo.isChecked();
                if (switchState == Boolean.TRUE)

                {
                    Toast.makeText(PMovieActivity.this, "Added to Favourites", Toast.LENGTH_LONG).show();
                    Log.d("Insert: ", "Inserting ..");
                    db.addFav(new Fav(movie_title, wurl, yurl, genre, synopsis, rating, rating1, director, actor, writer, poster, runtime, released));


                } else {
                    Toast.makeText(PMovieActivity.this, "Removed from Favourites", Toast.LENGTH_LONG).show();
                    db.deleteFav(new Fav(movie_title));
                }

            }
        });

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        y = yurl.split("embed/");
        Log.d("#@#@", y[0]);
        Log.d("#@#@", y[1]);
        View loading_bar = findViewById(R.id.loading_spinner);
        loading_bar.setVisibility(GONE);
    }

    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(y[1]); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent movieIntent = new Intent(PMovieActivity.this, MovieActivity.class);
        movieIntent.putExtra("mname", movie_title);
        // Start the new activity
        startActivity(movieIntent);
    }
}




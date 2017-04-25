package com.aditya.flickwiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.rating;
import static com.aditya.flickwiz.R.id.runtime;
import static com.aditya.flickwiz.R.id.yurl;
import static com.aditya.flickwiz.R.id.actor;
import static com.aditya.flickwiz.R.id.director;
import static com.aditya.flickwiz.R.id.genre;
import static com.aditya.flickwiz.R.id.poster;
import static com.aditya.flickwiz.R.id.wurl;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Aditya on 09/04/17.
 */

public class FavPMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    DatabaseHandler db = new DatabaseHandler(this);
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    String y[];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favpmovie);
        Intent intent = getIntent();

        final String name = intent.getStringExtra("movie_title");
        List<Fav> favs = db.getAllFavs();

        for (Fav cn : favs) {
            if (cn.getName().equals(name)) {
                TextView yurlview = (TextView) findViewById(yurl);
                yurlview.setText(cn.getYurl());

                TextView mname = (TextView) findViewById(R.id.name);
                mname.setText(name);
                TextView wurlview = (TextView) findViewById(wurl);
                wurlview.setText(cn.getWurl());
                String r[];
                r=cn.getReleased().split(" ");
                TextView yearview = (TextView) findViewById(R.id.year) ;
                yearview.setText("("+r[2]+")");
                TextView synopsisview = (TextView) findViewById(R.id.description);
                synopsisview.setText("Description : " + cn.getSynopsis());

                TextView genreview = (TextView) findViewById(genre);
                genreview.setText("Genre : " + cn.getGenre());

                TextView directorview = (TextView) findViewById(director);
                directorview.setText(cn.getDirector());

                TextView actorview = (TextView) findViewById(actor);
                actorview.setText(cn.getActor());



                TextView runtimeview = (TextView) findViewById(runtime) ;
                runtimeview.setText("Runtime : "+cn.getRuntime());
                TextView imdbrating = (TextView) findViewById(R.id.imdb);
                TextView rtrating = (TextView) findViewById(R.id.rt);
                imdbrating.setText(cn.getRating() );
                rtrating.setText(cn.getRating1());
                ImageView pview = (ImageView) findViewById(poster);
                Picasso.with(getApplicationContext()).load(cn.getPoster()).into(pview);

                final Switch makefavo = (Switch) findViewById(R.id.makefav);
                makefavo.setChecked(true);

                makefavo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // check current state of a Switch (true or false).
                            Toast.makeText(FavPMovieActivity.this, "Removed from Favourites", Toast.LENGTH_LONG).show();
                            db.deleteFav(new Fav(name));


                    }
                });


                youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
                youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

                y=cn.getYurl().split("embed/");
                Log.d("**!!",y[1]);
            }


         }
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
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent FavmovieIntent = new Intent(FavPMovieActivity.this, FavouriteActivity.class);
        startActivity(FavmovieIntent);
    }
}
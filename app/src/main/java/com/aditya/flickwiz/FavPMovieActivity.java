package com.aditya.flickwiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.aditya.flickwiz.R.id.yurl;
import static com.aditya.flickwiz.R.id.actor;
import static com.aditya.flickwiz.R.id.director;
import static com.aditya.flickwiz.R.id.genre;
import static com.aditya.flickwiz.R.id.poster;
import static com.aditya.flickwiz.R.id.wurl;
import static com.aditya.flickwiz.R.id.yurl;

/**
 * Created by Aditya on 09/04/17.
 */

public class FavPMovieActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);

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
                yurlview.setText("Trailer : " + cn.getYurl());

                TextView mname = (TextView) findViewById(R.id.name);
                mname.setText("Name : " + name);
                TextView wurlview = (TextView) findViewById(wurl);
                wurlview.setText("Wikipedia : " + cn.getWurl());

                TextView synopsisview = (TextView) findViewById(R.id.description);
                synopsisview.setText("Description : " + cn.getSynopsis());

                TextView genreview = (TextView) findViewById(genre);
                genreview.setText("Genre : " + cn.getGenre());

                TextView directorview = (TextView) findViewById(director);
                directorview.setText("Director : " + cn.getDirector());

                TextView actorview = (TextView) findViewById(actor);
                actorview.setText("Actor : " + cn.getActor());

                TextView ratingview = (TextView) findViewById(R.id.rating);
                ratingview.setText("Rating : " + cn.getRating() + " (IMDB), " + cn.getRating1() + " (Rotten Tomatoes)");

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



            }
         }
        }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent FavmovieIntent = new Intent(FavPMovieActivity.this, FavouriteActivity.class);
        startActivity(FavmovieIntent);
    }
}
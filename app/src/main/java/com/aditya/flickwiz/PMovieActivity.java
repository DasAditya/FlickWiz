package com.aditya.flickwiz;

/**
 * Created by Aditya on 25/03/17.
 */

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

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class PMovieActivity extends AppCompatActivity {


    String movie_title;


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
        View loading_bar = findViewById(R.id.loading_spinner);
        loading_bar.setVisibility(GONE);
        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Name : " + movie_title);
        TextView wurlview = (TextView) findViewById(R.id.wurl);
        wurlview.setText("Wikipedia : " + wurl);
        TextView yurlview = (TextView) findViewById(R.id.yurl);
        yurlview.setText("Trailer : " + yurl);
        TextView synopsisview = (TextView) findViewById(R.id.description);
        synopsisview.setText("Description : " + synopsis);

        TextView genreview = (TextView) findViewById(R.id.genre);
        genreview.setText("Genre : " + genre);

        TextView directorview = (TextView) findViewById(R.id.director);
        directorview.setText("Director : " + director);

        TextView actorview = (TextView) findViewById(R.id.actor);
        actorview.setText("Actor : " + actor);

        TextView ratingview = (TextView) findViewById(R.id.rating);
        ratingview.setText("Rating : " + rating + " (IMDB), " + rating1 + " (Rotten Tomatoes)");

        ImageView pview = (ImageView) findViewById(R.id.poster);
        Picasso.with(getApplicationContext()).load(poster).into(pview);
        List<Fav> favs = db.getAllFavs();

        for (Fav cn : favs) {
            Log.d("&*####",cn.getName());
            if (cn.getName().equals(movie_title)) {
                Log.d("***" , "same");
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
                    db.addFav(new Fav(movie_title,wurl,yurl,genre,synopsis,rating,rating1,director,actor,writer,poster,runtime,released));


                }

                else {
                    Toast.makeText(PMovieActivity.this, "Removed from Favourites", Toast.LENGTH_LONG).show();
                    db.deleteFav(new Fav(movie_title));
                }

            }
        });
    }




    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent movieIntent = new Intent(PMovieActivity.this, MovieActivity.class);
        movieIntent.putExtra("mname", movie_title);
        // Start the new activity
        startActivity(movieIntent);
    }
}




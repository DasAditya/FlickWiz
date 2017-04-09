package com.aditya.flickwiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Aditya on 25/03/17.
 */



    public class FavouriteActivity extends AppCompatActivity {


    String movie_title;
    final ArrayList<Details> words = new ArrayList<Details>();


    DatabaseHandler db = new DatabaseHandler(this);
    public MovieAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Fav> favs = db.getAllFavs();


        setContentView(R.layout.movie_list);

        final ArrayList<Movie> favos = new ArrayList<Movie>();
        adapter = new MovieAdapter(this, favos);
        for (Fav cn : favs) {

            // Create a list of words
                favos.add(new Movie(cn.getName(),cn.getGenre()));
//            TextView name = (TextView) findViewById(R.id.name);
//            name.setText("Name : " +  cn.getName());
//            TextView wurlview = (TextView) findViewById(wurl);
//            wurlview.setText("Wikipedia : " + wurl);
//            TextView yurlview = (TextView) findViewById(yurl);
//            yurlview.setText("Trailer : " + yurl);
//            TextView synopsisview = (TextView) findViewById(R.id.description);
//            synopsisview.setText("Description : " + synopsis);
//
//            TextView genreview = (TextView) findViewById(genre);
//            genreview.setText("Genre : " + genre);
//
//            TextView directorview = (TextView) findViewById(director);
//            directorview.setText("Director : " + director);
//
//            TextView actorview = (TextView) findViewById(R.id.actor);
//            actorview.setText("Actor : " + actor);
//
//            TextView ratingview = (TextView) findViewById(R.id.rating);
//            ratingview.setText("Rating : " + rating + " (IMDB), " + rating1 + " (Rotten Tomatoes)");
//
//            ImageView pview = (ImageView) findViewById(poster);
//            Picasso.with(getApplicationContext()).load(poster).into(pview);
//            List<Fav> favs = db.getAllFavs();

        }

        if(favos.size()!=0) {

            ListView listView = (ListView) findViewById(R.id.list);

            // Make the {@link ListView} use the {@link MovieAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Movie} in the list.
            listView.setAdapter(adapter);
            View loading_bar = findViewById(R.id.loading_spinner);
            loading_bar.setVisibility(GONE);

            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int pos,
                                        long id) {

                    Movie movie = favos.get(pos);
                    // TODO Auto-generated method stub
                    Log.d("############","Items " +  movie.getTitle() );
                    Intent FavMovieIntent = new Intent(FavouriteActivity.this, FavPMovieActivity.class);
                    FavMovieIntent.putExtra("movie_title", movie.getTitle());
                    // Start the new activity
                    startActivity(FavMovieIntent);
                }

            });

        } else {

            View loading_bar = findViewById(R.id.loading_spinner);
            loading_bar.setVisibility(GONE);

            TextView emptystate = (TextView) findViewById(R.id.empty_view);
            emptystate.setText("Time to start liking some movies");

        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(FavouriteActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}



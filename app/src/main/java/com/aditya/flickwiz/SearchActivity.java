package com.aditya.flickwiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.aditya.flickwiz.R.id.movienamesubmit;


/**
 * Created by Aditya on 28/03/17.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.input);

        Button movies = (Button) findViewById(movienamesubmit);

        // Set a click listener on that View
        movies.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent movieIntent = new Intent(SearchActivity.this, MovieActivity.class);
                EditText moviename = (EditText) findViewById(R.id.moviename);
                String mname = moviename.getText().toString();
                movieIntent.putExtra("mname", mname);
                // Start the new activity
                startActivity(movieIntent);
            }
        });

    }
}

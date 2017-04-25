/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aditya.flickwiz;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.aditya.flickwiz.R.id.movienamesubmit;
import static com.aditya.flickwiz.R.id.moviepage;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";

    private RelativeLayout layoutMain;
    private RelativeLayout movieSearch;
    private RelativeLayout favView;
    private LinearLayout layoutContent;
    private boolean isOpen1 = false;
    private boolean isOpen2 = false;
    private RecyclerView recView;
    //  private DerpAdapter adapter;
    private ArrayList listData;

    private FloatingActionButton fab1, fab2, close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        layoutContent = (LinearLayout) findViewById(R.id.layout_content);
        layoutMain = (RelativeLayout) findViewById(R.id.layout_main);
        movieSearch = (RelativeLayout) findViewById(R.id.movie_search);
        favView = (RelativeLayout) findViewById(R.id.fav);

//        TextView movies = (TextView) findViewById(moviepage);
//
//        // Set a click listener on that View
//        movies.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                Intent movieIntent = new Intent(MainActivity.this, SearchActivity.class);
//                // Start the new activity
//                startActivity(movieIntent);
//            }
//        });



//        TextView favourites = (TextView) findViewById(R.id.favourite);
//
//        // Set a click listener on that View
//        favourites.setOnClickListener(new OnClickListener() {
//            // The code in this method will be executed when the colors category is clicked on.
//            @Override
//            public void onClick(View view) {
//                Intent favouriteIntent = new Intent(MainActivity.this, FavouriteActivity.class);
//
//                // Start the new activity
//                startActivity(favouriteIntent);
//            }
//        });



        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                EditText et = (EditText) findViewById(R.id.moviename);
                et.requestFocus();
                viewMenu1();
            }
        });
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                viewMenu2();
            }
        });
        close = (FloatingActionButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                close();
            }
        });

        Button movies = (Button) findViewById(movienamesubmit);

        // Set a click listener on that View
        movies.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent movieIntent = new Intent(MainActivity.this, MovieActivity.class);
                EditText moviename = (EditText) findViewById(R.id.moviename);
                String mname = moviename.getText().toString();
                movieIntent.putExtra("mname", mname);
                // Start the new activity
                startActivity(movieIntent);
            }
        });
    }

    private void viewMenu1() {
        if (!isOpen1) {
            int x = layoutContent.getLeft()+(layoutContent.getRight()/4);
            int y = layoutContent.getBottom()/2;

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());
            fab1.setVisibility(GONE);
            close.setVisibility(VISIBLE);
//            ActionBar Color Change
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.movie)));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.movieDark));
            }

            Animator anim = ViewAnimationUtils.createCircularReveal(movieSearch, x, y, startRadius, endRadius);
            movieSearch.setVisibility(VISIBLE);
            anim.start();
            isOpen1 = true;
        }
    }

    private void viewMenu2() {
        if (!isOpen2) {

            int x = layoutContent.getRight()-(layoutContent.getRight()/4);
            int y = layoutContent.getBottom()/2;

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());
            fab1.setVisibility(GONE);
            close.setVisibility(VISIBLE);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.favPrimary)));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.favPrimaryDark));
            }

            Animator anim = ViewAnimationUtils.createCircularReveal(favView, x, y, startRadius, endRadius);
            favView.setVisibility(VISIBLE);
            anim.start();
            isOpen2 = true;
            Intent favouriteIntent = new Intent(MainActivity.this, FavouriteActivity.class);

                // Start the new activity
                startActivity(favouriteIntent);
        }


    }

    private void close() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        if (isOpen1) {
            int x = layoutContent.getLeft() + (layoutContent.getRight() / 4);
            int y = layoutContent.getBottom() / 2;

            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;
            fab1.setVisibility(VISIBLE);
            close.setVisibility(GONE);
            Animator anim = ViewAnimationUtils.createCircularReveal(movieSearch, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    movieSearch.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
            isOpen1 = false;
        }
        if (isOpen2) {
            int x = layoutContent.getRight() - (layoutContent.getRight() / 4);
            int y = layoutContent.getBottom() / 2;

            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;
            fab1.setVisibility(VISIBLE);
            close.setVisibility(GONE);
            Animator anim = ViewAnimationUtils.createCircularReveal(favView, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    favView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
            isOpen2 = false;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();
    }

}

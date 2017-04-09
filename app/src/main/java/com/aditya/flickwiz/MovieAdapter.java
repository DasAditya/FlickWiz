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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link MovieAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Movie} objects.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {





    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Movie} object located at this position in the list
        Movie currentMovie = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.title);
        nameTextView.setText(currentMovie.getTitle());

        TextView genreTextView = (TextView) listItemView.findViewById(R.id.synopsis);
        // Get the default translation from the currentMovie object and set this text on
        // the default TextView.
        genreTextView.setText(currentMovie.getGenre());

        TextView description_view = (TextView) listItemView.findViewById(R.id.description);
       //description_view.setText(currentMovie.getSynopsis());

        ImageView ratingimageview = (ImageView)listItemView.findViewById(R.id.ratingimage);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

            imageView.setVisibility(View.VISIBLE);

        View textContainer = listItemView.findViewById(R.id.text_container);



        return listItemView;
    }
}
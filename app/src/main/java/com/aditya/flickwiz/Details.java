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

/**
 * {@link Details} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation, and an image for that word.
 */
public class Details {

    private String released;

    private String runtime;
    private String genre;
    private String plot;
    private String rating,rating1;
    private String poster;
    private String director, writer, actor;

    public Details(String mreleased, String mruntime, String mgenre, String mdirector, String mactor, String mwriter, String mplot, String mposter ) {
        released = mreleased;
        runtime = mruntime;
        genre = mgenre;
        director = mdirector;
        actor = mactor;
        writer = mwriter;
        plot = mplot;
        poster = mposter;
    }
 public Details(String mreleased, String mruntime, String mgenre, String mdirector, String mactor, String mwriter, String mplot, String mposter, String mrating , String mrating1) {
        released = mreleased;
        runtime = mruntime;
        genre = mgenre;
        director = mdirector;
        actor = mactor;
        writer = mwriter;
        plot = mplot;
        poster = mposter;
        rating = mrating ;
        rating1 = mrating1 ;
    }


    public String getGenre() {
        return genre;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getWriter() {
        return writer;
    }

    public String getRating() {
        return rating;
    }

    public String getRating1() {
        return rating1;
    }



    @Override
    public String toString() {
        return "Details{" +
                "released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", plot='" + plot + '\'' +
                ", rating='" + rating + '\'' +
                ", poster='" + poster + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
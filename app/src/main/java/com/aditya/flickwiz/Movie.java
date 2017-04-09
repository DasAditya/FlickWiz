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
 * {@link Movie} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Miwok translation, and an image for that word.
 */
public class Movie {

    /** Default translation for the word */
    private String title; //mDefaultTranslation;

    private String type;
    /** Miwok translation for the word */
    private String genre; //mMiwokTranslation;
    private String synopsis;
    private String rating;
    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private String wurl, yurl;
    private int ratingimage = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;


    public Movie(String mtitle, String mgenre) {
        title = mtitle;
        genre = mgenre;
    }

    public Movie(String mtitle) {
        title = mtitle;

    }

    public Movie(String mtitle, String mtype, String msynopsis, String mwurl, String myurl ) {
        title = mtitle;
        type = mtype;
        synopsis=msynopsis;
        wurl = mwurl;
        yurl = myurl;
    }

    public Movie(String mtitle, String msynopsis, String mwurl, String myurl ) {
        title = mtitle;
        synopsis=msynopsis;
        wurl = mwurl;
        yurl = myurl;
    }


    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }

    public String getWurl() {
        return wurl;
    }
    public String getYurl() {
        return yurl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getRating() {
        return rating;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getRatingimage() {
        return ratingimage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
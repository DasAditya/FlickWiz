package com.aditya.flickwiz;

/**
 * Created by Aditya on 08/04/17.
 */

public class Fav {

    //private variables
    private int _id;
    private String _name, wurl,yurl,genre,synopsis,rating,rating1,director,actor,writer,poster,runtime,released;

    // Empty constructor
    public Fav(){

    }

    public Fav(int id, String name){
        this._id = id;
        this._name = name;
    }
    // constructor
    public Fav(String name){
        this._name = name;
    }

    public Fav(String name, String wurl, String yurl, String genre, String synopsis, String rating, String rating1, String director, String actor, String writer, String poster, String runtime, String released){
        this._name = name;
        this.wurl = wurl;
        this.yurl = yurl;
        this.genre = genre;
        this.synopsis = synopsis;
        this.rating =rating;
        this.rating1=rating1;
        this.director=director;
        this.actor=actor;
        this.writer=writer;
        this.poster=poster;
        this.runtime=runtime;
        this.released=released;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }

    public String getRating() {
        return rating;
    }

    public String getRating1() {
        return rating1;
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

    public String getWurl() {
        return wurl;
    }

    public String getYurl() {
        return yurl;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setWurl(String wurl) {
        this.wurl = wurl;
    }

    public void setYurl(String yurl) {
        this.yurl = yurl;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }


}
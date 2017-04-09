package com.aditya.flickwiz;

/**
 * Created by Aditya on 09/04/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // favs table name
    private static final String TABLE_FAVS = "favos";

    // favs Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private static final String genre = "genre";
    private static final String rating = "rating";
    private static final String rating1 = "rating1";
    private static final String director = "director";
    private static final String actor = "actor";
    private static final String writer = "writer";
    private static final String wurl   = "wurl";
    private static final String yurl = "yurl";
    private static final String synopsis = "synopsis";
    private static final String poster = "poster";
    private static final String runtime = "runtime";
    private static final String released = "released";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVS_TABLE = "CREATE TABLE " + TABLE_FAVS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +wurl + " TEXT," + yurl + " TEXT," +genre + " TEXT," +synopsis + " TEXT," +rating + " TEXT," +rating1 + " TEXT," +director + " TEXT," +actor + " TEXT," +writer + " TEXT," +poster + " TEXT," +runtime + " TEXT," +released + " TEXT" +")";
        db.execSQL(CREATE_FAVS_TABLE);
    }
     //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new fav
    void addFav(Fav fav) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fav.getName()); // Fav Name
        values.put(wurl, fav.getWurl()); // Fav Name
        values.put(yurl, fav.getYurl()); // Fav Name
        values.put(genre, fav.getGenre()); // Fav Name
        values.put(synopsis, fav.getSynopsis()); // Fav Name
        values.put(rating, fav.getRating()); // Fav Name
        values.put(rating1, fav.getRating1()); // Fav Name
        values.put(director, fav.getDirector()); // Fav Name
        values.put(actor, fav.getActor()); // Fav Name
        values.put(writer, fav.getWriter()); // Fav Name
        values.put(poster, fav.getPoster()); // Fav Name
        values.put(runtime, fav.getRuntime()); // Fav Name
        values.put(released, fav.getReleased()); // Fav Name

        // Inserting Row
        db.insert(TABLE_FAVS, null, values);
        db.close(); // Closing database connection
    }


    // Getting All favs
    public List<Fav> getAllFavs() {
        List<Fav> contactList = new ArrayList<Fav>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAVS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fav fav = new Fav();
                fav.setID(Integer.parseInt(cursor.getString(0)));
                fav.setName(cursor.getString(1));
                fav.setWurl(cursor.getString(2));
                fav.setYurl(cursor.getString(3));
                fav.setGenre(cursor.getString(4));
                fav.setSynopsis(cursor.getString(5));
                fav.setRating(cursor.getString(6));
                fav.setRating1(cursor.getString(7));
                fav.setDirector(cursor.getString(8));
                fav.setActor(cursor.getString(9));
                fav.setWriter(cursor.getString(10));
                fav.setPoster(cursor.getString(11));
                fav.setRuntime(cursor.getString(12));
                fav.setReleased(cursor.getString(13));
                // Adding fav to list
                contactList.add(fav);
            } while (cursor.moveToNext());
        }

        // return fav list
        return contactList;
    }

    // Updating single fav
    public int updateFav(Fav fav) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fav.getName());

        // updating row
        return db.update(TABLE_FAVS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(fav.getID()) });
    }

    // Deleting single fav
    public void deleteFav(Fav fav) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVS, KEY_NAME + " = ?",
                new String[] { String.valueOf(fav.getName()) });
        db.close();
    }


    // Getting favs Count
    public int getfavsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FAVS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
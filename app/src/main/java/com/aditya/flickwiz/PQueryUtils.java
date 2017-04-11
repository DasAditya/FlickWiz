package com.aditya.flickwiz;

/**
 * Created by Aditya on 27/03/17.
 */


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class PQueryUtils {

    /** Sample JSON response for a USGS query */

    public static final String LOG_TAG = PQueryUtils.class.getSimpleName();



    private PQueryUtils() {
    }

    /**
     * Return a list of {@link Movie} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Details> extractFeaturesFromEarthquakes(String earthquakeJson) {

        ArrayList<Details> details = new ArrayList<>();


        if(TextUtils.isEmpty(earthquakeJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject object = new JSONObject(earthquakeJson);


            //for(int i=0;i<object.length();i++) {


                   // JSONObject object1 = object.getJSONObject();
                    String released = object.getString("Released");
                    String runtime = object.getString("Runtime");
                    String genre = object.getString("Genre");
                    String director = object.getString("Director");
                    String writer = object.getString("Writer");
                    String actors = object.getString("Actors");
                    String plot = object.getString("Plot");
                    String poster = object.getString("Poster");



                      String rating = object.getString("imdbRating");
                      Random random = new Random();
                      details.add(new Details(released, runtime, genre, director, writer, actors, plot, poster, rating, Integer.toString(Math.abs(random.nextInt(60) % 100)+40) + "%"));

            //}

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        }
        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return details;
    }

    private static URL createURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if(url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error Response code : " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the json response", e);
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Details> fetchEarthQuakeData(String requestURL) {
        URL url = createURL(requestURL);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
            System.out.println(jsonResponse);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Details> earthquakes = extractFeaturesFromEarthquakes(jsonResponse);
        return earthquakes;
    }
}
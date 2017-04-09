package com.aditya.flickwiz;

/**
 * Created by Aditya on 27/03/17.
 */



import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
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

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Movie} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Movie> extractFeaturesFromMovies(String earthquakeJson) {

        ArrayList<Movie> movies = new ArrayList<>();


        if(TextUtils.isEmpty(earthquakeJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding movies to

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject object = new JSONObject(earthquakeJson);
            JSONObject array = object.getJSONObject("Similar");

            for(int i=0;i<array.length()-1;i++) {

                JSONArray info = array.getJSONArray("Info");
                for (int j=0;j<info.length();j++)      //it was info.length
                {
                    JSONObject insideinfo = info.getJSONObject(j);
                    String name = insideinfo.getString("Name");
                    String description = insideinfo.getString("wTeaser");
                    String yurl = insideinfo.getString("yUrl");
                    String wurl = insideinfo.getString("wUrl");
                    String type = insideinfo.getString("Type");
                    Log.d("&*&*",name);
                    if(type.equals("movie") || type.equals("Movie"))

                        movies.add(new Movie(name, description,wurl,yurl));

                }

                JSONArray similarresuls = array.getJSONArray("Results");
                for (int j=0;j<similarresuls.length();j++)
                {
                    JSONObject insideinfo = similarresuls.getJSONObject(j);

                    String name = insideinfo.getString("Name");
                    String description = insideinfo.getString("wTeaser");
                    String yurl = insideinfo.getString("yUrl");
                    String wurl = insideinfo.getString("wUrl");
                    String type = insideinfo.getString("Type");

                    Log.d("&*&*&*",name);
                    if(type.equals("movie"))
                        movies.add(new Movie(name, description,wurl,yurl));

                }




            }

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        }
        catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of movies
        return movies;
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
            Log.e(LOG_TAG, "Problem retrieving the earthquake json response", e);
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

    public static List<Movie> fetchMovieData(String requestURL) {
        URL url = createURL(requestURL);

        Log.i(LOG_TAG, "This is fetchEarthquakeData.....");

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
            System.out.println(jsonResponse);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Movie> movies = extractFeaturesFromMovies(jsonResponse);
        return movies;
    }
}
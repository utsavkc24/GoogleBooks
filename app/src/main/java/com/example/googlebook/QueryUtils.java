package com.example.googlebook;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**	/**
 * Helper methods related to requesting and receiving books data from Google*/
public final class QueryUtils {
    /**Tag from log messages**/
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

/**
 * Create a private constructor because no one should ever create a {@link QueryUtils} object.**/
 private QueryUtils() {
 }
    /**
     * Query the Google Books dataset and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBooksData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s

        // Return the list of {@link Book}s
        return extractJsonResponse(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Book}
     * object that has been up from
     * parsing the given JSON response.
     */
    private static List<Book> extractJsonResponse(String bookJSON){
        //If the JSON string is empty or null, then return early.
        if(TextUtils.isEmpty(bookJSON)){
            return null;
        }
        //Create an empty ArrayList that we can start adding books to
        List<Book> bookList = new ArrayList<>();
        try {

            //Create a JSONObject from the JSON_STRING
            JSONObject rootObject = new JSONObject(bookJSON);
            // Extract the JSONArray associated with the key called "items",
            // which represents a list of items (or books).
            JSONArray itemsArray = rootObject.getJSONArray("items");
            //Object of the first item in an array using getJSONObject
            for(int i = 0; i < itemsArray.length(); i++) {
                JSONObject currentBook = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                //Get the title key.
                String title = volumeInfo.getString("title");

                //Extract the value for the key called "authors"
                String author;
                if (volumeInfo.has("authors")){
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    if(!volumeInfo.isNull("authors")) author = (String) authors.get(0);
                    else author = "unknown author";
                }else author = "missing info about author";
                //Extract the value for the key called "price" and "currency"
                double price = 0;
                String currency;
                JSONObject saleInfo = currentBook.getJSONObject("saleInfo");
                if (saleInfo.has("retailPrice")){
                    JSONObject retailBookPrice = saleInfo.getJSONObject("retailPrice");
                    price = retailBookPrice.getDouble("amount");
                    currency = retailBookPrice.getString("currencyCode");
                }else{
                    currency = "Not for sale";
                }
                //Get the Image link from imageLinks
                JSONObject imageLink = volumeInfo.getJSONObject("imageLinks");
                String imageUrl = imageLink.getString("smallThumbnail");
                //Get the Author name from authors key.
                String publisherName = volumeInfo.getString("publisher");
                //Adding object(title,author,publisher,price)
                Book bookObject = new Book(title,author,publisherName,price,currency,imageUrl);
                //Book bookObject = new Book(title);
                bookList.add(bookObject);
            }
        }catch (Exception e){
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the books JSON results", e);
        }
        return bookList;
    }
}

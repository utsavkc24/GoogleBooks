package com.example.googlebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {
    /**
     * Constant value for the Book Loader ID.We can choose any integer.
     * This really only comes into play if you're using multiple loader.
     */
    private static final int BOOKS_LOADER_ID = 1;
    /**
     * URL for books data from the Google dataset
     */
    private static String JSON_STRING = "https://www.googleapis.com/books/v1/volumes?q=";
    SearchView searchView;
    /**
     * Adapter for the list of books
     */
    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.list);
        searchView = (SearchView) findViewById(R.id.search_bar);
        // Create the adapter to convert the array to views
        mAdapter = new BookAdapter(getApplicationContext(), new ArrayList<Book>());
        // Attach the adapter to a ListView
        listView.setAdapter(mAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    JSON_STRING = JSON_STRING + query;
                    //Get a reference to the LoaderManeger,in order to interact with loaders.
                    LoaderManager loaderManager = getLoaderManager();
                    //Initialise the loader. pass in te ID constant defined above and pass
                    // in null for the bundle. Pass in this activity for the LoaderCallbacks parameter
                    //(which is valid because this activity implements the LoaderCallbacks interface).
                    loaderManager.initLoader(BOOKS_LOADER_ID, null, MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "No match found", Toast.LENGTH_LONG);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        //Create a new loader for the given URL.
        return new BookLoader(this, JSON_STRING);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        // If there is a valid list of {@link Books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        //Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
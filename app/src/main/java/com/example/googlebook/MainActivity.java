package com.example.googlebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {
    /**
     * Constant value for the Book Loader ID.We can choose any integer.
     * This really only comes into play if you're using multiple loader.
     */
    private static final int BOOKS_LOADER_ID = 1;

    /**
     * Get a reference to the LoaderManeger,in order to interact with loaders.
     *
     */
   private LoaderManager loaderManager = getLoaderManager();
    /**
     * URL for books data from the Google dataset
     */
    private static String JSON_STRING = "https://www.googleapis.com/books/v1/volumes";

    /**
     * Adapter for the list of books
     */
    private BookAdapter mAdapter;
    private String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.list);
        // Create the adapter to convert the array to views
        mAdapter = new BookAdapter(getApplicationContext(), new ArrayList<Book>());
        // Attach the adapter to a ListView
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            query = intent.getStringExtra(SearchManager.QUERY);
            if (!getSupportLoaderManager().hasRunningLoaders()){
                //Initialise the loader. pass in te ID constant defined above and pass
                // in null for the bundle. Pass in this activity for the LoaderCallbacks parameter
                //(which is valid because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(BOOKS_LOADER_ID, null, this);
            }
            loaderManager.restartLoader(BOOKS_LOADER_ID,null,this);
        }
    }

    /*
     To display the SearchView in the app bar, inflate the XML menu resource
         */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        //In the onCreateOptionsMenu() method that you created before,
        // associate the searchable configuration with the
        // SearchView by calling setSearchableInfo(SearchableInfo)
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
         SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
         return true;
    }



    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(JSON_STRING);
        Uri.Builder builder = uri.buildUpon();

        builder.appendQueryParameter("q",query);
        builder.appendQueryParameter("maxResult","9");

        //Create a new loader for the given URL.
        return new BookLoader(this, builder.toString());
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
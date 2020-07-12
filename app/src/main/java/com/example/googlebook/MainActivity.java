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
import android.widget.TextView;

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

    /** TextView that is displayed when the list is empty */
    private TextView textView;
    //hide loading indicator because the data has been loaded
    private View loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.empty_list);
        final ListView listView = (ListView) findViewById(R.id.list);
        // Create the adapter to convert the array to views
        mAdapter = new BookAdapter(getApplicationContext(), new ArrayList<Book>());
        // Attach the adapter to a ListView
        listView.setAdapter(mAdapter);
        listView.setEmptyView(textView);
        //hide loading indicator because the data has been loaded
        loadingIndicator = findViewById(R.id.loading_spinner);
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //Get details on the currently active default data network
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnected();
        if(!isConnected){
            loadingIndicator.setVisibility(View.GONE);
            // Set empty state text to display "No books found."
            textView.setText(R.string.empty_state);
        }
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
        textView.setVisibility(View.GONE);
        loadingIndicator.setVisibility(View.GONE);
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
        loadingIndicator.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);

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
        loadingIndicator.setVisibility(View.GONE);
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //Get details on the currently active default data network
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        assert networkInfo != null;
        if(networkInfo.isConnected()){
            // Set empty state text to display "No books found."
            textView.setText(R.string.empty_state);
        }else {
            // Set empty state text to display "No Internet."
            textView.setText(R.string.internet_state);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        //Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
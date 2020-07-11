package com.example.googlebook;

import android.content.Context;

import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;
public class BookLoader extends AsyncTaskLoader<List<Book>> {
    /**Query Url**/
    private String mUrl;

    /**
     * Construct a new {@link BookLoader}.
     *
     * @param context of the activity
     * @param mUrl to load data from
     */
    BookLoader(Context context, String mUrl) {
        super(context);
        this.mUrl =mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on background thread.
     *
     */
    @Nullable
    @Override
    public List<Book> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null
        if (mUrl == null) {
            return null;
        }
        //Perform the network request, parse the response, and extract a list of Books
        return QueryUtils.fetchBooksData(mUrl);
    }
}

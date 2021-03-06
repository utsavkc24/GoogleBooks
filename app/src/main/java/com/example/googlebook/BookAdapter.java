package com.example.googlebook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private static final String LOG_TAG = BookAdapter.class.getSimpleName();
    BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
        }
        // get the object located at this location
        Book currentLocation = getItem(position);
        assert currentLocation != null;

        // finding the titleTextView in  and setting value to it.
        TextView titleTextView = listItemView.findViewById(R.id.title);
        TextView authorTextView = listItemView.findViewById(R.id.author_name);
        TextView retailPriceTextView = listItemView.findViewById(R.id.retails_name);
        TextView currencyTextView = listItemView.findViewById(R.id.currency);
        ImageView imageView = listItemView.findViewById(R.id.book_image);
        // Populate the data into the template view using the data object
        titleTextView.setText(currentLocation.getmTitle());
        authorTextView.setText(currentLocation.getmAuthor());
        retailPriceTextView.setText(String.valueOf(currentLocation.getmAmount()));
        currencyTextView.setText(currentLocation.getmCurrency());
        Glide.with(getContext()).load(currentLocation.getmImageUrl()).into(imageView);
        return listItemView;
    }

}

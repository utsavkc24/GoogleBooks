package com.example.googlebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context,@NonNull ArrayList<Book> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            // If there's no view to re-use, inflate a brand new view for row
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list,parent,false);
        }
        // get the object located at this location
        Book currentLocation = getItem(position);

        // finding the titleTextView in  and setting value to it.
        TextView titleTextView = listItemView.findViewById(R.id.title);
        // Populate the data into the template view using the data object
        titleTextView.setText(currentLocation.getmTitle());
        return listItemView;
    }


}
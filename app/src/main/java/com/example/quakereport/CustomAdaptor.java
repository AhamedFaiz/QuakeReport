package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdaptor extends ArrayAdapter<Earthquake> {

    public CustomAdaptor(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private int getMagnitudeColor(double magnitude){

        switch ((int)magnitude){
            case 1: case 0 :
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            default:
                return R.color.magnitude10plus;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View earthquakeListItem = convertView;

        if(earthquakeListItem == null){

            earthquakeListItem = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_layout,parent,false);
        }

        Earthquake earthquakeItem = getItem(position);

        TextView magTextView = earthquakeListItem.findViewById(R.id.magTextView);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = ContextCompat.getColor(getContext(),getMagnitudeColor(earthquakeItem.getMagnitude()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //To format earthquake magnitude to 1 decimal place only
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(earthquakeItem.getMagnitude());
        magTextView.setText(output);


        TextView locationOffset = earthquakeListItem.findViewById(R.id.location_offset);
        TextView primaryLocation = earthquakeListItem.findViewById(R.id.primary_location);

        // to split the places in to 2 text views
        String place = earthquakeItem.getPlace();
        if(place.contains("of ")) {
            String[] splittedPlaces = place.split("of ");
            locationOffset.setText(splittedPlaces[0] + "of ");
            primaryLocation.setText(splittedPlaces[1]);
        }else {
            locationOffset.setText("Near the");
            primaryLocation.setText(place);
        }


        TextView dateTextView = earthquakeListItem.findViewById(R.id.dateTextView);

        // to convert unix time to format we needed
        Long timeInMilliSeconds = earthquakeItem.getDate();
        Date dateObject = new Date(timeInMilliSeconds);
        String dateToDisplay = formatDate(dateObject);
        dateTextView.setText(dateToDisplay);

        TextView timeTextView = earthquakeListItem.findViewById(R.id.timeTextView);
        String timeToDisplay = formatTime(dateObject);
        timeTextView.setText(timeToDisplay);


        return earthquakeListItem;
    }
}

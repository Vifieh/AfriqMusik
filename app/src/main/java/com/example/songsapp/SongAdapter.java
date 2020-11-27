package com.example.songsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class SongAdapter extends ArrayAdapter<Song> {
    private int mcolorResourceId;

    public SongAdapter(Activity context, ArrayList<Song> songs, int colorResourceId) {
        super(context, 0, songs);
        mcolorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Song currentSong = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentSong.getTitle());

        TextView yearTextView = (TextView) listItemView.findViewById(R.id.year_text_view);
        yearTextView.setText(currentSong.getYear());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_action);

        if (currentSong.hasImage()) {
            imageView.setImageResource(currentSong.getImageResourceId());
            //makes sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            //otherwise hide the ImageView
            imageView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mcolorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}

package com.example.songsapp;

public class Song {
    private String mTitle;
    private String mYear;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceId;

    public Song(String title, String year, int audioResourceId) {
        mTitle = title;
        mYear = year;
        mAudioResourceId = audioResourceId;
    }

    public Song(String title, String year, int imageResourceId, int audioResourceId) {
        mTitle = title;
        mYear = year;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getYear() {
        return mYear;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}

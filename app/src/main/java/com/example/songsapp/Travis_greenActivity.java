package com.example.songsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Travis_greenActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mediaPlayer.pause();
//                            mediaPlayer.seekTo();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume playback
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };
    //listener gets triggered when {@link MediaPlayer} has completed playing the audio file
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("You waited", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("All things new", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Nothing but the Blood", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Respond", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Be still", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Intentional", "2017", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Good and loved", "2019", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Made a way", "2018", R.drawable.travis_green, R.raw.song));
        songs.add(new Song("Broken Vessels", "2019", R.drawable.travis_green, R.raw.song));

        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_color);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //Get the {@link Song} at the given position the user clicked on
                Song song = songs.get(position);

                //release the media player if currently exists because we are about to
                // play a diffent sound
                releaseMediaPlayer();

                //request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //we have audio focus now


                    //play current song
                    mediaPlayer = MediaPlayer.create(Travis_greenActivity.this, song.getAudioResourceId());
                    //start song
                    mediaPlayer.start();

                    //setup a listener on the media so that we can stop and release the media pl
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    // cleanup the media player by releasing its resources
    private void releaseMediaPlayer() {
        //if the media player is not null then it maybe currently playing a sound
        if (mediaPlayer != null) {
            //regardless of the current state of the media player, release its resource
            // we no longer need it
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
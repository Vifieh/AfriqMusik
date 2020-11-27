package com.example.songsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Frank_edwardActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
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
        songs.add(new Song("Chioma", "2018", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("One Song", "2018", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("You are Good", "2019", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("Under the Canopy", "2017", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("Onghene Doh", "2018", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("Miyeruwe", "2019", R.drawable.frank_edward, R.raw.song));
        songs.add(new Song("Mma Mma", "2017", R.drawable.frank_edward, R.raw.song));

        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_color);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //Get the {@link Song} at the given position the user clicked on
                Song song = songs.get(position);

                //release the media player if currently exists because we are about to
                // play a different sound
                releaseMediaPlayer();

                //request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //we have audio focus now

                    //play current song
                    mediaPlayer = MediaPlayer.create(Frank_edwardActivity.this, song.getAudioResourceId());
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

            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
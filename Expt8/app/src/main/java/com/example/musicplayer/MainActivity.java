package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView trackName, currentPosititon, trackDuration;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackName = findViewById(R.id.track_name);
        seekBar = findViewById(R.id.seek_bar);
        currentPosititon = findViewById(R.id.current_position);
        trackDuration = findViewById(R.id.track_duration);
        Button playButton = findViewById(R.id.play_button);
        Button pauseButton = findViewById(R.id.pause_button);
        Button stopButton = findViewById(R.id.stop_button);

        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
        trackName.setText(mediaPlayer != null ? "Sample Audio" : "Error loading Audio");
        if(mediaPlayer != null)
        {
            seekBar.setMax(mediaPlayer.getDuration());
            trackDuration.setText(formatDuration(mediaPlayer.getDuration()));
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playButton.setOnClickListener(v -> {
            if(mediaPlayer != null && !mediaPlayer.isPlaying())
            {
                mediaPlayer.start();
                updateSeekBar();
            }
        });

        pauseButton.setOnClickListener(v -> {
            if(mediaPlayer != null && mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();
            }
        });

        stopButton.setOnClickListener(v -> {
            if(mediaPlayer != null)
            {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
                seekBar.setProgress(0);
                currentPosititon.setText("00:00");
            }
        });

        updateSeekBar();
    }

    private void updateSeekBar(){
        handler.postDelayed(()->{
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                currentPosititon.setText(formatDuration(mediaPlayer.getCurrentPosition()));
            }
            updateSeekBar();
        }, 1000);
    }

    private String formatDuration(int ms)
    {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms),TimeUnit.MILLISECONDS.toSeconds(ms)%60);
    }

    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }


}
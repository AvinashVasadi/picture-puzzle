package com.example.s528734.milestone;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer = MediaPlayer.create(this, R.raw.musicbackground);
        mediaPlayer.start();
    }

    public void onStart(View v){
       startActivity(new Intent(this,PregameSettings.class));
    }

    public void OnSettings(View v){
       startActivity(new Intent(this,Settings.class));
    }

    public void OnHighScore(View v){
        startActivity(new Intent(this,score.class));
    }

    public void OnLeaderBoard(View v){
        startActivity(new Intent(this,LeaderBoard.class));
    }
}

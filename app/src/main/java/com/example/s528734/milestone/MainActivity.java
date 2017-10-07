package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View v){
        Intent start = new Intent(this, PregameSettings.class);
        startActivity(start);
    }

    public void OnSettings(View v){
        Intent settings = new Intent(this, Settings.class);
        startActivity(settings);
    }

    public void OnHighScore(View v){
        Intent highscore = new Intent(this, score.class);
        startActivity(highscore);
    }

    public void OnLeaderBoard(View v){
        Intent leaderboard = new Intent(this, LeaderBoard.class);
        startActivity(leaderboard);
    }
}

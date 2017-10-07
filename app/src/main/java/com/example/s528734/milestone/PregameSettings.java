package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PregameSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame_settings);
    }

    public void OnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void OnLetsGo(View v){
        Intent letsgo = new Intent(this, Game.class);
        startActivity(letsgo);
    }

}

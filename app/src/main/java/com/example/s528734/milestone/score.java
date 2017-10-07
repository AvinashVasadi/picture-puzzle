package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }

    public void ScoreBoardOnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}

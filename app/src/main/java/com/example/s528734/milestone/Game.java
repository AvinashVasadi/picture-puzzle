package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void OnHomePage(View v){
        Intent homepage = new Intent(this, MainActivity.class);
        startActivity(homepage);
    }
    public void undo(View v)
    {
        Toast.makeText(getApplicationContext(), "last action is undone", Toast.LENGTH_LONG).show();
    }
    public void rotate(View v)
    {
        Toast.makeText(getApplicationContext(), "the selected block is rotated", Toast.LENGTH_LONG).show();
    }
}

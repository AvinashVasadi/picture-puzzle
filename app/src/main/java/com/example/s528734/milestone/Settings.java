package com.example.s528734.milestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void SettingsOnBack(View v){
        Intent onBack = new Intent(this, MainActivity.class);
        startActivity(onBack);
    }

    public void OnResetToDefaults(View v){
        Toast.makeText(getApplicationContext(), "settings are reset to default", Toast.LENGTH_LONG).show();
    }
}

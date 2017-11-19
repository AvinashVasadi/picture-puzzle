package com.example.s528734.milestone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    public int DBeasy;
    public int DBhard;
    public long DBeasytime;
    public long DBhardtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        messWithDB();
    }

    private void messWithDB()
    {
        System.out.println("messwithDB is called");
        SQLitedatbase helper = new SQLitedatbase(getApplicationContext());
        SQLiteDatabase TestDB = helper.getWritableDatabase();
        ContentValues movie = new ContentValues();

        Cursor results = TestDB.query("test", new String[]{"_id", "easy", "hard", "easytime", "hardtime"}, null, null, null, null, null);
        for(int i = 0; i<results.getCount(); i++)
        {
            System.out.println("entered for loop");
            results.moveToPosition(i);
            DBeasy = results.getInt(1);
            DBhard = results.getInt(2);
            DBeasytime = results.getInt(3);
            DBhardtime = results.getInt(4);
            System.out.println(results.getInt(0) + " " + results.getInt(1) + " " + results.getInt(2) + results.getInt(3));
        }

        helper.close();

        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText(Integer.toString(DBeasy));
        tvh.setText(Integer.toString(DBhard));
    }

    //score for time mode
    public void timeScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText(Long.toString(DBeasytime));
        tvh.setText(Long.toString(DBhardtime));
    }

    //score for normal mode
    public void normalScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText(Integer.toString(DBeasy));
        tvh.setText(Integer.toString(DBhard));
    }

    public void ScoreBoardOnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

}

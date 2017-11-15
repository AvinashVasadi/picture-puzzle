package com.example.s528734.milestone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class score extends AppCompatActivity {

    int easyScore;
    int mediumScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        int movescount = getIntent().getIntExtra("movescount", 1);
        if(movescount == 1){
            easyScore = 0;
        } else {
            easyScore = 1000/movescount;
        }

        TextView easyscoreTV = (TextView)findViewById(R.id.textView8);
        easyscoreTV.setText(String.valueOf(easyScore));

        int mediumMovesCount = getIntent().getIntExtra("mediumMovesCount", 1);
        if(mediumMovesCount == 1){
            mediumScore = 0;
        } else {
            mediumScore = 4000/mediumMovesCount;
        }

        TextView mediumscoreTV = (TextView)findViewById(R.id.textView9);
        mediumscoreTV.setText(String.valueOf(mediumScore));

        messWithDB();
    }

    public void ScoreBoardOnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    private void messWithDB()
    {
        ScoreSQLiteOpenHelper helper = new ScoreSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase scoreDB = helper.getWritableDatabase();
        ContentValues score = new ContentValues();

        score.put("easy", easyScore);
        score.put("medium", mediumScore);
        score.put("hard", 1000);
        scoreDB.insert("scores",null, score);

        Cursor results = scoreDB.query("scores", new String[]{"_id", "easy", "medium", "hard"}, "", null, null, null, null);
        for(int i = 0; i<results.getCount(); i++)
        {
            results.moveToPosition(i);
            System.out.println(results.getString(1) + " " + results.getInt(2));
        }

        helper.close();
    }

    public void timeScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvm= (TextView) findViewById(R.id.textView9);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText("387");
        tvm.setText("210");
        tvh.setText("98");
    }
}

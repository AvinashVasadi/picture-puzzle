package com.example.s528734.milestone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class score extends AppCompatActivity {

    int easyScore;
    int mediumScore;
    int scoremode;
    long easytimecount;
    long mediumtimecount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        scoremode = getIntent().getIntExtra("scoremode", 4);
        if(scoremode == 1)
        {
            System.out.println("easyscore mode is called");
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
        }
        if(scoremode == 2){
            System.out.println("hardscore mode is called");
            easytimecount = getIntent().getLongExtra("scorecount",5);
        }



        messWithDB();
    }

    public void ScoreBoardOnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    private void messWithDB()
    {
        System.out.println("messwithDB is called");
        ScoreInfo score = new ScoreInfo();
        score.easy = 1;
        score.hard = 2;
        score.easytime = 10;
        score.hardtime = 20;

        Backendless.Data.of( ScoreInfo.class ).save(score, new AsyncCallback<ScoreInfo>() {

            @Override
            public void handleResponse(ScoreInfo response) {
                Log.d("DB","saved"+response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });

//        ScoreSQLiteOpenHelper helper = new ScoreSQLiteOpenHelper(getApplicationContext());
//        SQLiteDatabase scoreDB = helper.getWritableDatabase();
//        ContentValues score = new ContentValues();
//
//        if(scoremode == 1){
//            score.put("easy", easyScore);
//            score.put("hard", mediumScore);
//            score.put("easytime", 107);
//            score.put("hardtime", 108);
//        }
//        if(scoremode == 2){
//            score.put("easytime", easytimecount);
//            score.put("hardtime", 108);
//        }
//
//        scoreDB.insert("scores",null, score);
//
//        Cursor results = scoreDB.query("scores", new String[]{"_id", "easy", "hard", "easytime", "hardtime"}, "", null, null, null, null);
//        for(int i = 0; i<results.getCount(); i++)
//        {
//            results.moveToPosition(i);
//            System.out.println(results.getString(1) + " " + results.getInt(2));
//        }
//
//        helper.close();
    }

    //score for time mode
    public void timeScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvm= (TextView) findViewById(R.id.textView9);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText("387");
        tvm.setText("210");
        tvh.setText("98");
    }

    //score for normal mode

    public void normalScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvm= (TextView) findViewById(R.id.textView9);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText("547");
        tvm.setText("345");
        tvh.setText("189");
    }

}

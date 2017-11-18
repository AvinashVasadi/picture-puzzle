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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class score extends AppCompatActivity {

    int easyScore = 0;
    int mediumScore = 0;
    int scoremode;
    long easytimecount = 0;
    long hardtimecount = 0;

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
//            TextView easyscoreTV = (TextView)findViewById(R.id.textView8);
//            easyscoreTV.setText(String.valueOf(easyScore));

            int mediumMovesCount = getIntent().getIntExtra("mediumMovesCount", 1);
            if(mediumMovesCount == 1){
                mediumScore = 0;
            } else {
                mediumScore = 4000/mediumMovesCount;
            }

//            TextView mediumscoreTV = (TextView)findViewById(R.id.textView10);
//            mediumscoreTV.setText(String.valueOf(mediumScore));
        }
        if(scoremode == 2){
            System.out.println("hardscore mode is called");

            easytimecount = getIntent().getLongExtra("timecount",5);
//            TextView tve= (TextView) findViewById(R.id.textView8);
//            tve.setText(Long.toString(easytimecount));


            hardtimecount = getIntent().getLongExtra("mediumtimecount", 5);
//            TextView tvh= (TextView) findViewById(R.id.textView10);
//            tvh.setText(Long.toString(hardtimecount));


        }

        printOrderDetails();
    }

    public void ScoreBoardOnBack(View v){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    private void messWithDB()
    {
        System.out.println("messwithDB is called");
        ScoreInfo score = new ScoreInfo();

        if(scoremode == 1){
            if(easyScore>DBeasy){
                score.setEasy(easyScore);
            }
            if(mediumScore>DBhard){
                score.setHard(mediumScore);
            }
        }

        if(scoremode == 2){
            if(easytimecount>DBeasytime){
                score.setEasytime(easytimecount);
            }
            if(hardtimecount>DBhardtime){
                score.setHardtime(hardtimecount);
            }
        }

        Backendless.Data.of( ScoreInfo.class ).save(score, new AsyncCallback<ScoreInfo>() {

            @Override
            public void handleResponse(ScoreInfo response)
            {
                Log.d("DB","saved"+response);
                response.setEasy(easyScore);
                response.setEasytime(easytimecount);


                Backendless.Data.of(ScoreInfo.class).save(response, new AsyncCallback<ScoreInfo>() {
                    @Override
                    public void handleResponse(ScoreInfo response) {

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
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

    public int DBeasy;
    public int DBhard;
    public long DBeasytime;
    public long DBhardtime;

    public void printOrderDetails(){

        IDataStore<ScoreInfo> orderStorage = Backendless.Data.of(ScoreInfo.class);
        DataQueryBuilder query=DataQueryBuilder.create();
        orderStorage.find(query, new AsyncCallback<List<ScoreInfo>>() {

            @Override
            public void handleResponse(List<ScoreInfo> response) {
                Log.d("Printing : ","Order Details: "+response);
                DBeasy = response.get(0).easy;
                DBhard = response.get(0).hard;
                DBeasytime = response.get(0).easytime;
                DBhardtime = response.get(0).hardtime;


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });
        Toast.makeText(getApplicationContext(), "Printing Order Details", Toast.LENGTH_SHORT).show();
        // helper.close();

        messWithDB();

    }





    //score for time mode
    public void timeScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText(Long.toString(easytimecount));
        tvh.setText(Long.toString(hardtimecount));
    }

    //score for normal mode

    public void normalScore(View v)
    {
        TextView tve= (TextView) findViewById(R.id.textView8);
        TextView tvh= (TextView) findViewById(R.id.textView10);
        tve.setText(Integer.toString(easyScore));
        tvh.setText(Integer.toString(mediumScore));
    }

}

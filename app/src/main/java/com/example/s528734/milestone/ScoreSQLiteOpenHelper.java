package com.example.s528734.milestone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by S528770 on 11/4/2017.
 */

public class ScoreSQLiteOpenHelper extends SQLiteOpenHelper {

    public ScoreSQLiteOpenHelper(Context context) {

        super(context, "ScoreDB", null, 1);

    }

    private static final String createDB = "create table scores(" + "_id integer primary key autoincrement," +
            "easy integer," +
            "medium integer," +
            "hard integer)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(createDB);
        } catch (Exception e){
            Log.d("scoreDB",e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists scores");

        onCreate(sqLiteDatabase);
    }


}

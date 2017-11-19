package com.example.s528734.milestone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by S528770 on 11/18/2017.
 */

public class SQLitedatbase extends SQLiteOpenHelper {

    public SQLitedatbase(Context context) {

        super(context, "TestDB", null, 1);

    }

    private static final String createDB = "create table test(" + "_id integer primary key autoincrement," +
            "easy integer," +
            "hard integer," +
            "easytime integer," +
            "hardtime integer)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            sqLiteDatabase.execSQL(createDB);
        } catch (Exception e){
            Log.d("TestDB",e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists movies");

        onCreate(sqLiteDatabase);
    }


}


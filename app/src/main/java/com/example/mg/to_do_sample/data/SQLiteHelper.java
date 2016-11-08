package com.example.mg.to_do_sample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mukesh on 1/10/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MediSys.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +Contract.Contracts.TABLE_NAME + " (" +
                    Contract.Contracts._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    Contract.Contracts.COLUMN_NAME_TITLE +  " TEXT NOT NULL, " +
                    Contract.Contracts.COLUMN_NAME_DESCRIPTION +  " TEXT NOT NULL " +
                    " )";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.Contracts.TABLE_NAME;



    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

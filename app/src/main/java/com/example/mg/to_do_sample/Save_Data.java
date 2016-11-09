package com.example.mg.to_do_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.mg.to_do_sample.data.Contract;
import com.example.mg.to_do_sample.data.SQLiteHelper;

import java.util.ArrayList;

/**
 * Created by mg on 11/8/16.
 */

public class Save_Data extends AsyncTask<Void,Void,Boolean>{
    SQLiteHelper sqLiteHelper;
    Context context;
    Todo_item todo_item;
    public Save_Data(Context context,Todo_item todo_item){
        this.context=context;
        sqLiteHelper=new SQLiteHelper(context);
        this.todo_item=todo_item;
    }


    @Override
    protected Boolean doInBackground(Void... voids) {

        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.Contracts.COLUMN_NAME_TITLE,todo_item.getTitle());
        values.put(Contract.Contracts.COLUMN_NAME_DESCRIPTION,todo_item.getDescription());
        long id=db.insert(Contract.Contracts.TABLE_NAME,null,values);

        return id!=-1;
    }

    @Override
    protected void onPostExecute(Boolean result) {



    }
}

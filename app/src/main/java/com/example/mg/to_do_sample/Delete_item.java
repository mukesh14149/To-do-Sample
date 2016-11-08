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

public class Delete_item extends AsyncTask<Void,Void,Void> {

    ArrayList<Todo_item> todo_item_list;
    SQLiteHelper sqLiteHelper;
    Context context;
    Todo_item todo_item;

    public Delete_item(Context context,Todo_item todo_item){
        this.context=context;
        sqLiteHelper=new SQLiteHelper(context);
        this.todo_item=todo_item;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        String selection = Contract.Contracts.COLUMN_NAME_TITLE + " = ? AND "+ Contract.Contracts.COLUMN_NAME_DESCRIPTION+"= ?";
        String[] selectionArgs = {todo_item.getTitle(),todo_item.getDescription()};
        db.delete(Contract.Contracts.TABLE_NAME,selection,selectionArgs);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {



    }
}

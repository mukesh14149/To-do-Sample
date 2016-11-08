package com.example.mg.to_do_sample.data;


import android.provider.BaseColumns;

/**
 * Created by mukesh on 1/10/16.
 */
public final class Contract {



    private Contract(){}

    public static class Contracts implements BaseColumns{

        final public static String TABLE_NAME="TO_DO";
        final public static String COLUMN_NAME_TITLE="title";
        final public static String COLUMN_NAME_DESCRIPTION="description";
    }

}

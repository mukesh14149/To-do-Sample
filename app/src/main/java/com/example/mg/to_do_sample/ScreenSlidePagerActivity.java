package com.example.mg.to_do_sample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mg.to_do_sample.data.Contract;
import com.example.mg.to_do_sample.data.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public ArrayList<Todo_item> todo_item_list=new ArrayList<Todo_item>();
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    List<ScreenSlidePageFragment> fList;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_pager);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Showdata_in_page showdata_in_page=new Showdata_in_page(getIntent().getIntExtra("pos",0));
        showdata_in_page.execute();


        // Instantiate a ViewPager and a PagerAdapter.

    }




    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ArrayList<Todo_item> todo_items;
        public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<Todo_item> todo_items) {
            super(fm);
            this.todo_items=todo_items;
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(todo_items.get(position));
        }

        @Override
        public int getCount() {
            return todo_items.size();
        }
    }


    public class Showdata_in_page extends AsyncTask<Void,Void,Void> {
        public Context context;
        int pos;
        Showdata_in_page(int pos){
            this.pos=pos;
        }

        SQLiteHelper sqLiteHelper=new SQLiteHelper(getApplicationContext());
        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase db=sqLiteHelper.getReadableDatabase();

            String[] projection={
                    Contract.Contracts.COLUMN_NAME_TITLE,
                    Contract.Contracts.COLUMN_NAME_DESCRIPTION,
            };

            Cursor cursor = db.query(
                    Contract.Contracts.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    Todo_item todo_item=new Todo_item();
                    String title=cursor.getString(cursor.getColumnIndex(Contract.Contracts.COLUMN_NAME_TITLE));
                    todo_item.setTitle(title);
                    String description=cursor.getString(cursor.getColumnIndex(Contract.Contracts.COLUMN_NAME_DESCRIPTION));
                    todo_item.setDescription(description);

                    todo_item_list.add(todo_item);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(todo_item_list.size()>0) {
                mPager = (ViewPager) findViewById(R.id.pager);
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), todo_item_list);
                mPager.setAdapter(mPagerAdapter);
                mPager.setCurrentItem(pos);
            }
        }
    }
}


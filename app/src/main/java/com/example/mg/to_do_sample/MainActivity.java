package com.example.mg.to_do_sample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mg.to_do_sample.data.Contract;
import com.example.mg.to_do_sample.data.SQLiteHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Dialog_Box.OnCompleteListener{
    Adapter adapter;
    RecyclerView recyclerView;
    ArrayList<Todo_item> todo_item_list=new ArrayList<Todo_item>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton imageButton=(ImageButton)findViewById(R.id.image_toolbar);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFragment newFragment = new Dialog_Box();
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("Todo_array",todo_item_list);
                newFragment.setArguments(bundle);
                newFragment.show(ft, "dialog");
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Showdata showdata=new Showdata();
        showdata.execute();


        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                viewHolder.itemView.setVisibility(View.GONE);

                Todo_item tempremArc;
                tempremArc=todo_item_list.get(viewHolder.getAdapterPosition());

                todo_item_list.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                adapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(),todo_item_list.size());

                Delete_item delete_item=new Delete_item(getApplicationContext(),tempremArc);
                delete_item.execute();


            }

        });
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("yo budyy");
                                    Intent intent=new Intent(getApplicationContext(),ScreenSlidePagerActivity.class);
                                    intent.putExtra("pos",position);
                                 //   intent.putParcelableArrayListExtra("todo_item_list",todo_item_list);
                                    startActivity(intent);

                        // TODO Handle item click
                    }
                })
        );

    }
    public void fab(View view){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = new Dialog_Box();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("Todo_array",todo_item_list);
        newFragment.setArguments(bundle);
        newFragment.show(ft, "dialog");
    }

    @Override
    public void onComplete(String title, String Description) {
        Todo_item todo_item=new Todo_item();
        todo_item.setTitle(title);
        todo_item.setDescription(Description);
        todo_item_list.add(todo_item);
        adapter.notifyDataSetChanged();
    }




    public class Showdata extends AsyncTask<Void,Void,Void>{

        SQLiteHelper sqLiteHelper=new SQLiteHelper(getApplicationContext());
        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase db=sqLiteHelper.getReadableDatabase();

            String[] projection={
                    Contract.Contracts.COLUMN_NAME_TITLE,
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
                    todo_item_list.add(todo_item);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            adapter=new Adapter(getApplicationContext(),todo_item_list);
            recyclerView.setAdapter(adapter);
            super.onPostExecute(aVoid);
        }
    }




}

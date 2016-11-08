package com.example.mg.to_do_sample;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mukesh on 14/10/16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private Context context;
    private ArrayList<Todo_item> todo_item_list;

    public Adapter(Context context, ArrayList<Todo_item> todo_item_list) {
        this.context=context;
        this.todo_item_list=todo_item_list;
    }

    private Context getContext() {
        return context;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case


        TextView title;
        Context context;

        public ViewHolder(View item, Context context) {

            super(item);
            this.context=context;
            title=(TextView) item.findViewById(R.id.Title_view);

        }




    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,getContext());
        return viewHolder;

    }




    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {

        final Todo_item todo_item = todo_item_list.get(position);

        holder.title.setText(todo_item.getTitle());

    }


    @Override
    public int getItemCount() {
        return todo_item_list.size();
    }
}

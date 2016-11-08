package com.example.mg.to_do_sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mg on 11/8/16.
 */

public class Dialog_Box extends DialogFragment {

    public interface OnCompleteListener {
        public void onComplete(String title,String Description);
    }
    private OnCompleteListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //getting proper access to LayoutInflater is the trick. getLayoutInflater is a                   //Function



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.dialog_box, null);
            builder.setView(view);
            builder.setTitle("Enter Details").setNeutralButton(
                    "", null);

            final EditText title=(EditText)view.findViewById(R.id.title);
            final EditText description=(EditText)view.findViewById(R.id.description);

            builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Do something else
                    Todo_item todo_item=new Todo_item();
                    todo_item.setTitle(title.getText().toString());
                    todo_item.setDescription(description.getText().toString());

                    Save_Data save_data=new Save_Data(getActivity(),todo_item);
                    save_data.execute();
                    mListener.onComplete(title.getText().toString(),description.getText().toString());
                    getDialog().dismiss();

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Do something else
                    getDialog().dismiss();
                }
            });




        return builder.create();
    }
}

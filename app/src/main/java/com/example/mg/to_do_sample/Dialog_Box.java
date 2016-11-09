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
import android.widget.Toast;

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
         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.dialog_box, null);
            builder.setView(view);
            builder.setTitle("Enter Details").setNeutralButton(
                    "", null);


            final EditText title=(EditText)view.findViewById(R.id.title);
            final EditText description=(EditText)view.findViewById(R.id.description);

            title.setError(null);
            description.setError(null);
            builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Do something else
                    if(!title.getText().toString().isEmpty()&&!description.getText().toString().isEmpty()) {


                        Todo_item todo_item = new Todo_item();
                        todo_item.setTitle(title.getText().toString());
                        todo_item.setDescription(description.getText().toString());

                        Save_Data save_data = new Save_Data(getActivity(), todo_item);
                        save_data.execute();
                        mListener.onComplete(title.getText().toString(), description.getText().toString());
                        getDialog().dismiss();

                    }else{
                        Toast.makeText(getActivity(), "Some Filled are empty", Toast.LENGTH_SHORT).show();
                    }
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

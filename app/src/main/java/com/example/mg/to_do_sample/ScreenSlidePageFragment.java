package com.example.mg.to_do_sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {

    private int page;
    private String title;
    private String description;

    public static ScreenSlidePageFragment newInstance(Todo_item todo_item) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle=new Bundle();
        System.out.println("yooooo"+todo_item.getTitle().toString());
        bundle.putString("Description",todo_item.getDescription().toString());
        bundle.putParcelable("todo_object",todo_item);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);




        TextView textView=(TextView)rootView.findViewById(R.id.frag_description);
        Log.i("asdf","asdf");
        System.out.println(getArguments().getString("Description"));
        textView.setText(getArguments().getString("Description").toString());


        return rootView;

    }


}

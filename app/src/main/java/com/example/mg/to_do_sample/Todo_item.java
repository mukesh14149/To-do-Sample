package com.example.mg.to_do_sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mg on 11/8/16.
 */

public class Todo_item implements Parcelable{
    String Title;
    String Description;

    public void setTitle(String title){
        Title=title;
    }

    public String getTitle(){
        return Title;
    }
    public Todo_item(){

    }
    public void setDescription(String description){
        Description=description;
    }

    public String getDescription(){
        return Description;
    }

    private Todo_item(Parcel in){
        Title=in.readString();
        Description=in.readString();
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(Title);
        parcel.writeString(Description);
    }
    public static final Parcelable.Creator<Todo_item> CREATOR = new Parcelable.Creator<Todo_item>()
    {

        @Override
        public Todo_item createFromParcel(Parcel parcel)
        {
            return new Todo_item(parcel);
        }

        @Override
        public Todo_item[] newArray(int i)
        {
            return new Todo_item[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}

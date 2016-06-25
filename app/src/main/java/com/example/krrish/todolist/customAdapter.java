package com.example.krrish.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by krrish on 12/07/2015.
 */
public class customAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Tasks_data> list;
    LayoutInflater inflater;
    public customAdapter(Activity activity, ArrayList<Tasks_data> list) {
        this.activity=activity;
        Collections.sort(list,new DateComparator());
        this.list=list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater==null){
            inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listview,null);
        }
        TextView heading= (TextView) convertView.findViewById(R.id.date);
        TextView description= (TextView) convertView.findViewById(R.id.tvdescription);

        Tasks_data data=list.get(position);
        heading.setText(data.getDate());
        description.setText(data.toString());

        return convertView;
    }
}

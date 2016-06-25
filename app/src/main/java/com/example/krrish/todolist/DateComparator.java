package com.example.krrish.todolist;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by krrish on 16/07/2015.
 */
public class DateComparator implements Comparator<Tasks_data> {

    @Override
    public int compare(Tasks_data lhs, Tasks_data rhs) {
        DateFormat dateFormat=new SimpleDateFormat("dd/mm/yyyy");
        Date ldate=null,rdate=null;

        try {
            ldate=dateFormat.parse(lhs.getDate());

            rdate=dateFormat.parse(rhs.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

       if(ldate.before(rdate))
           return-1;
       else if(ldate.after(rdate))
            return 1;
      else
            return 0;
    }
}

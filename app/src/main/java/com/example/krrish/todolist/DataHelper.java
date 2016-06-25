package com.example.krrish.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    //Tasks_data data;
    private static final String Database="Taskslist2";
    private static final int DBversion=1;
    private static final String TABLENAME = "taskslist1";
    private static final String SLNO = "slno";
    private static final String TASKTITLE = "title";
    private static final String DESCRIPTION = "Description";
    private static final String DATE = "Date";

    public DataHelper(Context context) {
        super(context, Database, null, DBversion);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("tableoncreate","started");
        String query= "create table " + TABLENAME + "(" + SLNO + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASKTITLE + " TEXT," + DESCRIPTION + " TEXT," + DATE+" TEXT);";
        db.execSQL(query);
        Log.d("tablecreatd", "executed");
        //this.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void insert(Tasks_data data)
    {

        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TASKTITLE,data.getTasks());
        values.put(DESCRIPTION, data.getDescription());
        values.put(DATE, data.getDate());
        long sl_no=db.insert(TABLENAME, null, values);
        data.setSl_no(sl_no);

        db.close();

    }
    ArrayList<Tasks_data> getAllData()
    {
        ArrayList <Tasks_data> tasks=new ArrayList<>();

        db=this.getWritableDatabase();
        String query="select * from "+TABLENAME;
        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Tasks_data data=new Tasks_data();
            data.setTasks(cursor.getString(1));
            data.setDescription(cursor.getString(2));
            data.setDate(cursor.getString(3));
            data.setSl_no(cursor.getLong(0));
            tasks.add(data);
        }
        return tasks;
    }

 void remove( long slno){
     db.execSQL("DELETE FROM "+ TABLENAME+" WHERE SLNO = '" + slno + "'");

 }


}

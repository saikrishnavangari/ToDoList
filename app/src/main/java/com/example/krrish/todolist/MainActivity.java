package com.example.krrish.todolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.util.Log.*;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemLongClickListener {
    DataHelper helper;
    ArrayList<Tasks_data> list=new ArrayList<>();
    ListView li;
    Calendar cl;
    int year,date, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setlist();
        cl=Calendar.getInstance();
        date=cl.get(Calendar.DAY_OF_MONTH);
        month=cl.get(Calendar.MONTH);
        year=cl.get(Calendar.YEAR);
    }
   void setlist()
    {
        helper=new DataHelper(this);
        list=helper.getAllData();
        li= (ListView) findViewById(R.id.listView);
       customAdapter customadapter=new customAdapter(this,list);
        //helper.insert();
        li.setAdapter(customadapter);
       li.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) this);
        d("tableinsert", "started");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addNew) {

            LayoutInflater inflater=LayoutInflater.from(this);
            View propmt_view=inflater.inflate(R.layout.dialog, null);

            final EditText etTitle= (EditText) propmt_view.findViewById(R.id.title);
            final EditText etDescription= (EditText) propmt_view.findViewById(R.id.description);
            //final EditText etDate= (EditText) propmt_view.findViewById(R.id.date);
            final DatePicker datePicker= (DatePicker) propmt_view.findViewById(R.id.datepicker);
            final AlertDialog dialog=new AlertDialog.Builder(this).create();

            Button btnsave= (Button) propmt_view.findViewById(R.id.btnsave);
            Button btncancel= (Button) propmt_view.findViewById(R.id.btncance);



                btnsave.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick (View v){
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();

                        if(etTitle.getText().toString().isEmpty()||etDescription.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
                        }

                        else {
                            Tasks_data data = new Tasks_data();
                            data.setTasks(etTitle.getText().toString());
                            data.setDescription(etDescription.getText().toString());
                            data.setDate(new StringBuilder()
                                    // Month is 0 based so add 1
                                    .append(day + 1).append("/")
                                    .append(month).append("/")
                                    .append(year).append(" ").toString());
                            helper.insert(data);
                            dialog.dismiss();
                            setlist();
                        }
                }
                }

                );
                btncancel.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick (View v){
                    dialog.cancel();
                   Log.d("insdemetod","yes");
                }
                }
                );
                dialog.setView(propmt_view);
                dialog.show();
                return true;
            }

            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        long slno;

        slno= ((Tasks_data)parent.getItemAtPosition(position)).getSl_no();

        helper.remove(slno);
        setlist();
        return false;
    }

     /* @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       long slno;

        slno= ((Tasks_data)parent.getItemAtPosition(position)).getSl_no();

        helper.remove(slno);
        setlist();

    }*/
}

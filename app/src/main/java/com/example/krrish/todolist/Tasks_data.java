package com.example.krrish.todolist;

import java.util.Date;

/**
 * Created by krrish on 10/07/2015.
 */
public class Tasks_data {
    private String tasks;
    private String description;
    private String date;
    private long Sl_no;

    public long getSl_no() {
        return Sl_no;
    }

    public void setSl_no(long sl_no) {
        Sl_no = sl_no;
    }


    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return tasks + "\n \n" +description+ "\n \n" +date;
    }
}

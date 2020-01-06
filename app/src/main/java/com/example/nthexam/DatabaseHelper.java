package com.example.nthexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper( Context context) {
        super( context, "Exam.db", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key,password text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "drop table if exists user" );
        //db.execSQL( "drop table if exists exam" );
    }

    public boolean insert(String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(  );
        cv.put( "email" ,email);
        cv.put( "password",password );
        long check = db.insert("user",null,cv);
        if(check==-1)
            return false;
        return true;
    }

    public Boolean check(String email,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from user where email =? and password =?";
        Cursor c = db.rawQuery( sql,new String[]{email,password} );
        if(c.getCount()>0)
        {
            return true;
        }
        return false;
    }

    public Boolean check_mail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from user where email =? ";
        Cursor c = db.rawQuery( sql,new String[]{email} );
        if(c.getCount()>0)
        {
            return true;
        }
        return false;
    }
}

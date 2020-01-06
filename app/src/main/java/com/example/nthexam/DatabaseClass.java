package com.example.nthexam;

import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseClass {

    public void create(SQLiteDatabase db) {

        db.execSQL( "drop table if exists exam" );
        db.execSQL("create table exam(name varchar(50) primary key,sequence_no int,date varchar(10),exam_type varchar(10))");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('GATE Aptitude Test',1,'"+date+"','GATE') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('Practice Paper(CSE)',2,'"+ date +"','GATE') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('Practice Paper(ECE)',3,'"+date+"','GATE') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('Practice Paper(ME)',4,'"+date+"','GATE') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('Practice Paper(CE)',5,'"+date+"','GATE') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('CAT Aptitude Test',1,'"+date+"','CAT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('CAT Mock Test',2,'"+date+"','CAT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('CAT Reasoning Test',3,'"+date+"','CAT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('CAT Algebra Test',4,'"+date+"','CAT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('JEE ADVANCE I',1,'"+date+"','IIT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('JEE ADVANCE II',2,'"+date+"','IIT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('JEE PHYSICS',3,'"+date+"','IIT') " );
        db.execSQL( "insert into exam(name,sequence_no,date,exam_type) values('JEE MATHS',4,'"+date+"','IIT') " );

    }

}

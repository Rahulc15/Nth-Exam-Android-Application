package com.example.nthexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button apply;
    private String GATE="gate",IIT="iit",CAT="cat";
    private ArrayList<Item> items;
    SQLiteDatabase mydb;
    Intromanager intromanager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_homepage );
        toolbar = (Toolbar)findViewById( R.id.app_bar );
        apply = (Button)findViewById( R.id.go );
        setSupportActionBar( toolbar );
        mydb = this.openOrCreateDatabase( "Exam",MODE_PRIVATE,null );
        DatabaseClass d1 = new DatabaseClass();
        d1.create(mydb);
        intromanager = new Intromanager(this );

        PreferenceManager.setDefaultValues( this,R.xml.preferences,false );
        SharedPreferences sharedPreferences;
        sharedPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences( getApplicationContext() );

        items = new ArrayList<Item>();
        String check_gate = String.valueOf( sharedPreferences.getBoolean( GATE,false ) ) ;
        String check_cat = String.valueOf( sharedPreferences.getBoolean( CAT,false ) ) ;
        String check_iit = String.valueOf( sharedPreferences.getBoolean( IIT,false ) ) ;


        if(check_gate.equals( "true" ))
        {
            String query = "select * from exam where exam_type= 'GATE'";
            Cursor c = mydb.rawQuery( query,null );

            if(c!=null)
            {
                while (c.moveToNext()) {
                    items.add( create_item( c ) );
                }
            }
        }
        if(check_cat.equals( "true" ))
        {
            String query = "select * from exam where exam_type= 'CAT'";
            Cursor c = mydb.rawQuery( query,null );

            if(c!=null)
            {
                while (c.moveToNext()) {
                    items.add( create_item( c ) );
                }
            }
        }
        if(check_iit.equals( "true" ))
        {
            String query = "select * from exam where exam_type= 'IIT'";
            Cursor c = mydb.rawQuery( query,null );

            if(c!=null)
            {
                while (c.moveToNext()) {
                    items.add( create_item( c ) );
                }
            }
        }


        RecyclerView recyclerView = (RecyclerView)findViewById( R.id.r_view);
        ListAdapter adapter = new ListAdapter(items,getApplicationContext() );
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );



        apply.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences;
                sharedPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences( getApplicationContext() );System.out.println("hello1");
                items = new ArrayList<Item>();

                String check_gate = String.valueOf( sharedPreferences.getBoolean( GATE,false ) ) ;
                String check_cat = String.valueOf( sharedPreferences.getBoolean( CAT,false ) ) ;
                String check_iit = String.valueOf( sharedPreferences.getBoolean( IIT,false ) ) ;


                if(check_gate.equals( "true" ))
                {
                    String query = "select * from exam where exam_type= 'GATE'";
                    Cursor c = mydb.rawQuery( query,null );

                    if(c!=null)
                    {
                        while (c.moveToNext()) {
                            items.add( create_item( c ) );
                        }
                    }
                }
                if(check_cat.equals( "true" ))
                {
                    String query = "select * from exam where exam_type= 'CAT'";
                    Cursor c = mydb.rawQuery( query,null );

                    if(c!=null)
                    {
                        while (c.moveToNext()) {
                            items.add( create_item( c ) );
                        }
                    }
                }
                if(check_iit.equals( "true" ))
                {
                    String query = "select * from exam where exam_type= 'IIT'";
                    Cursor c = mydb.rawQuery( query,null );

                    if(c!=null)
                    {
                        while (c.moveToNext()) {
                            items.add( create_item( c ) );
                        }
                    }
                }


                RecyclerView recyclerView = (RecyclerView)findViewById( R.id.r_view);
                ListAdapter adapter = new ListAdapter(items,getApplicationContext() );
                recyclerView.setAdapter( adapter );
                recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

            }



        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if(id==R.id.action_settings)
        {
            startActivity( new Intent( HomepageActivity.this,SettingsActivity.class ) );
            return true;
        }
        if(id==R.id.logout)
        {
            SharedPreferences sharedPreferences;
            sharedPreferences = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences( getApplicationContext() );
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean( GATE,false );
            editor.putBoolean( CAT,false );
            editor.putBoolean( IIT,false );
            editor.commit();
            intromanager.setFirst(false);
            startActivity( new Intent( HomepageActivity.this,LoginActivity.class ) );
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    private Item create_item(Cursor c)
    {
        Item item = new Item();
        item.setTest_name( c.getString( c.getColumnIndex( "name" ) ) );
        item.setS_no( c.getInt( c.getColumnIndex( "sequence_no" ) ) );
        item.setCreated_date( c.getString( c.getColumnIndex( "date" ) ) );
        item.setExam_name( c.getString( c.getColumnIndex( "exam_type" ) ) );
        return item;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable(false);
        builder.setMessage( "Are you sure you want to exit ?" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomepageActivity.this.finish();
            }
        } );
        builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        } );
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

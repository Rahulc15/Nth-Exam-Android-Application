package com.example.nthexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings );
        toolbar = (Toolbar)findViewById( R.id.app_bar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Settings" );

        if(findViewById( R.id.fragment_container )!=null)
        {
            if(savedInstanceState!=null)
                return;
            getFragmentManager().beginTransaction().add( R.id.fragment_container,new SettingsFragment() ).commit();
        }


    }

}

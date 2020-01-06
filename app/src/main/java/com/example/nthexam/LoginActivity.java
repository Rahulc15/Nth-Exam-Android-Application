package com.example.nthexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText Email,password;
    Intromanager intromanager;
    private TextView register;
    private Button login_btn;
    DatabaseHelper db;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        intromanager = new Intromanager(this );
        register = (TextView)findViewById( R.id.signup );
        Email = (EditText)findViewById( R.id.email );
        password = (EditText)findViewById( R.id.pass );
        login_btn = (Button)findViewById( R.id.btn_login );
        db = new DatabaseHelper( this );

        pref = getSharedPreferences( "com.example.nthexam",MODE_PRIVATE ) ;

        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent( LoginActivity.this,SignUp.class );
                startActivity( i1 );
                finish();
            }
        } );

        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = Email.getText().toString();
                String s2 = password.getText().toString();
                if(db.check( s1,s2 )==true)
                {
                    intromanager.setFirst( true );
                    Intent i1 = new Intent( LoginActivity.this, HomepageActivity.class );
                    startActivity( i1 );
                    finish();
                }
                else
                {
                    Toast.makeText( getApplicationContext(),"Incorrect Details! Please Try Again",Toast.LENGTH_LONG ).show();
                    Email.setText( "" );
                    password.setText( "" );
                }
            }
        } );
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable(false);
        builder.setMessage( "Are you sure you want to exit ?" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginActivity.this.finish();
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

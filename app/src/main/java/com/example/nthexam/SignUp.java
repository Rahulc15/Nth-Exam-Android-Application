package com.example.nthexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private EditText email,pass,cpass;
    private Button sign;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        email = (EditText)findViewById( R.id.new_mail );
        pass = (EditText)findViewById( R.id.pass1 );
        db = new DatabaseHelper( this );
        cpass = (EditText)findViewById( R.id.confirm_pass1 );
        sign = (Button)findViewById( R.id.btn_register );
        sign.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                String s2 = pass.getText().toString();
                String s3 = cpass.getText().toString();
                if(s1.equals( "" )||s2.equals( "" )||s3.equals( "" ))
                {
                    Toast.makeText(getApplicationContext(),"Please Fill All the Details",Toast.LENGTH_LONG ).show();
                }
                else
                {
                    if(s2.equals( s3 ))
                    {
                        if(db.check_mail( s1 )==true)
                        {
                            Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_LONG ).show();
                            email.setText("");
                        }
                        else
                        {
                            Boolean create = db.insert( s1,s2 );
                            if(create ==true) {
                                Toast.makeText( getApplicationContext(), "Account Created", Toast.LENGTH_LONG ).show();
                                Intent i1 = new Intent(SignUp.this, LoginActivity.class);
                                startActivity(i1);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(),"Error in creating Account",Toast.LENGTH_LONG ).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password DO NOT MATCH",Toast.LENGTH_LONG ).show();
                        pass.setText("");cpass.setText( "" );
                    }
                }
            }
        } );
    }

    @Override
    public void onBackPressed() {
        Intent i1 = new Intent( SignUp.this,LoginActivity.class );
        startActivity( i1 );
        finish();
    }
}

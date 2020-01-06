package com.example.nthexam;

import android.content.Context;
import android.content.SharedPreferences;

public class Intromanager {

    static SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Intromanager(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences( "first",0 );
        editor = pref.edit();
    }

    public void setFirst(Boolean isFirst)
    {
        editor.putBoolean( "check",isFirst );
        editor.commit();
    }

    public void setSecond(Boolean isSecond)
    {
        editor.putBoolean( "check_home",isSecond );
        editor.commit();
    }

    public static boolean Check()
    {
        return pref.getBoolean( "check",false );
    }

    public static boolean Check_second()
    {
        return pref.getBoolean( "check_home",false );
    }

}

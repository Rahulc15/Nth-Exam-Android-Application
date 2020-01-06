package com.example.nthexam;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    Button next,skip;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private Intromanager intromanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        intromanager = new Intromanager(this );
        if(Intromanager.Check()==true)
        {
            Intent i1 = new Intent( MainActivity.this,HomepageActivity.class );
            startActivity( i1 );
            finish();
        }
        else
            if(Intromanager.Check_second()==true)
            {
                Intent i1 = new Intent( MainActivity.this,LoginActivity.class );
                startActivity( i1 );
                finish();
            }

        setContentView( R.layout.activity_main );
        viewPager = (ViewPager)findViewById( R.id.view_pager );
        dotsLayout = (LinearLayout)findViewById( R.id.layoutDots );
        skip = (Button)findViewById( R.id.btn_skip );
        next = (Button)findViewById( R.id.btn_next );



        layouts = new int[]{R.layout.screen_1,R.layout.screen_2,R.layout.screen_3};

        addBottomDots( 0 );
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter( viewPagerAdapter );
        viewPager.addOnPageChangeListener( viewListener );
        skip.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intromanager.setSecond(true);
                Intent i1 = new Intent( MainActivity.this,LoginActivity.class );
                startActivity( i1 );
                finish();
            }
        } );

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem( 1 );
                if(current<layouts.length)
                {
                    viewPager.setCurrentItem( current );
                }
                else
                {
                    intromanager.setSecond(true);
                    Intent i1 = new Intent( MainActivity.this,LoginActivity.class );
                    startActivity( i1 );
                    finish();
                }
            }
        } );
    }


    private void addBottomDots(int position)
    {
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray( R.array.dot_active );
        int[] colorInactive = getResources().getIntArray( R.array.dot_inactive );
            dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++)
        {
            dots[i] = new TextView( this );
            dots[i].setText( Html.fromHtml( "&#8226;" ) );
            dots[i].setTextSize( 35 );
            dots[i].setTextColor( colorInactive[position] );
                dotsLayout.addView( dots[i] );
        }

        if(dots.length>0)
        {
            dots[position].setTextColor( colorActive[position] );
        }
    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addBottomDots( i );
            if(i==layouts.length-1)
            {
                next.setText( "PROCEED" );
                skip.setVisibility( View.GONE );
            }
            else
            {
                next.setText( "NEXT" );
                skip.setVisibility( View.VISIBLE );
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public class ViewPagerAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View v = layoutInflater.inflate( layouts[position],container,false );
            container.addView( v );

            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v = (View)object;
            container.removeView( v );
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable(false);
        builder.setMessage( "Are you sure you want to exit ?" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
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

package com.example.nthexam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    @NonNull

    private ArrayList<Item> arr = new ArrayList<Item>();
    private Context context;

    public ListAdapter(@NonNull ArrayList<Item> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.layout_listitem,viewGroup,false );
        ViewHolder holder = new ViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.t_name.setText( arr.get( i ).getTest_name() );
        viewHolder.s_no.setText( Integer.toString(arr.get( i ).getS_no()) );
        viewHolder.date_created.setText( arr.get( i ).getCreated_date() );
        viewHolder.exam_name.setText( arr.get( i ).getExam_name() );
        viewHolder.parentLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, arr.get( i ).getTest_name() + " --> " + Integer.toString( arr.get( i ).getS_no() ),Toast.LENGTH_LONG ).show();
            }
        } );
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView t_name,s_no,date_created,exam_name;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            t_name = itemView.findViewById( R.id.test_name);
            s_no = itemView.findViewById( R.id.s_no);
            date_created = itemView.findViewById( R.id.created_on);
            exam_name = itemView.findViewById( R.id.exam_type);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}

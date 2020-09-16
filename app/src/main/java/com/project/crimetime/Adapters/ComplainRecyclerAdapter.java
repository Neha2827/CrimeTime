package com.project.crimetime.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.project.crimetime.Classes.ComplainClass;
import com.project.crimetime.R;

import java.util.ArrayList;
/*
public class ComplainRecyclerAdapter extends RecyclerView.Adapter<ComplainRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<ComplainClass> list = new ArrayList<>();
    public ComplainRecyclerAdapter(ArrayList<ComplainClass> list, Context context){
        this.list = list;
        this.context = context;
    }

    /*@NonNull
    @Override
    public ComplainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_recycler_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }*/

    /* @Override
    public void onBindViewHolder(@NonNull final ComplainRecyclerAdapter.ViewHolder holder, int position) {
        ComplainClass complain = list.get(position);

        try{
            holder.heading.setText("Complan no. (" + complain.getComplainNo() + ")");
            holder.complainNo.setText("Your Complain Number - " + complain.getComplainNo());
            holder.details.setText(complain.getComplain());
            holder.status.setText("Status - " + complain.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        } */

/*
        holder.heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.extra.getVisibility() == View.GONE){
                    holder.extra.setVisibility(View.VISIBLE);
                }
                else {
                    holder.extra.setVisibility(View.GONE);
                }

            }
        });

        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.extra.getVisibility() == View.VISIBLE){
                    holder.extra.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, details, status, complainNo;
        LinearLayout extra;
        Button back;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.complain_heading);
            details = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            complainNo = itemView.findViewById(R.id.complain_no);
            extra = itemView.findViewById(R.id.extra);
            back = itemView.findViewById(R.id.back);
        }
    }
}
*/
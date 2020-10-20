package com.project.crimetime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.project.crimetime.Classes.ComplainClass;

public class AdminComplainAdapter extends FirestoreRecyclerAdapter<ComplainClass, AdminComplainAdapter.Holder> {


    public AdminComplainAdapter(@NonNull FirestoreRecyclerOptions<ComplainClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull ComplainClass model) {
        holder.mTvNo.setText(model.getComplainNo());
        holder.mTvDate.setText(model.getDate());
        holder.mTvComplain.setText(model.getComplain());
        holder.mTvPin.setText(model.getPin());
        holder.mTvPlace.setText(model.getAddress());
        holder.mTvName.setText(model.getName());
        holder.mTvContact.setText(model.getContact());
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_admin,parent,false);
        return new Holder(view);
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView mTvNo,mTvDate,mTvComplain,mTvPin,mTvPlace,mTvName,mTvContact;
        Button mButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTvComplain=itemView.findViewById(R.id.tv_blank_details);
            mTvDate=itemView.findViewById(R.id.tv_blank_date);
            mTvNo=itemView.findViewById(R.id.tv_blank_no);
            mTvPin=itemView.findViewById(R.id.tv_blank_pin);
            mTvPlace=itemView.findViewById(R.id.tv_blank_place);
            mTvName=itemView.findViewById(R.id.tv_blank_name);
            mTvContact=itemView.findViewById(R.id.tv_blank_contact);
            mButton=itemView.findViewById(R.id.btn_accept);
        }
    }
}

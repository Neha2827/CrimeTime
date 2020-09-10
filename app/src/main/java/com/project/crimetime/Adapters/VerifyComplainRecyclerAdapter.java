package com.project.crimetime.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.crimetime.AdminComplainOptions;
import com.project.crimetime.R;

import java.util.ArrayList;

public class VerifyComplainRecyclerAdapter extends RecyclerView.Adapter<VerifyComplainRecyclerAdapter.ItemHolder> {

    ArrayList<String> titles = new ArrayList<>();
    Context context;

    public VerifyComplainRecyclerAdapter(ArrayList<String> titles, Context context){
        this.titles = titles;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.verified_complain_layout, parent, false);
        ItemHolder ih = new ItemHolder(v);
        return ih;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String str = titles.get(position);

        holder.click.setText(str);

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminComplainOptions.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        Button click;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            click = itemView.findViewById(R.id.click);
        }
    }
}

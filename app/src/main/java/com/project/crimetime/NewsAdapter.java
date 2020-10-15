package com.project.crimetime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Article> articles;

    public NewsAdapter(Context context,ArrayList<Article> articles){
        this.articles=articles;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_news,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Article current=articles.get(position);

        holder.mTvTitle.setText(current.title);
        holder.mTvDesc.setText(current.description);
        Glide.with(context).load(current.urlToImage).into(holder.mIvImage);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvTitle;
        private ImageView mIvImage;
        private TextView mTvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle=itemView.findViewById(R.id.tv_title);
            mIvImage=itemView.findViewById(R.id.iv_news);
            mTvDesc=itemView.findViewById(R.id.tv_desc);
        }
    }
}

package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrimeRateReport extends AppCompatActivity {

    RecyclerView mRcNews;
    NewsAdapter adapter;
    TextView mTvReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_rate_report);
        mRcNews=findViewById(R.id.rv_news);
        mTvReport=findViewById(R.id.tv_report);
        mRcNews.setLayoutManager(new LinearLayoutManager(CrimeRateReport.this,RecyclerView.VERTICAL,false));

        OnGetNews();
    }

    private void OnGetNews() {
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);

        Call<Result> getNews=apiInterface.getNews("google-news","d0d45793d09c45789842968c4a90b42e");

        getNews.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result=response.body();
                ArrayList<Article> newsArticle=result.articleList;

                adapter=new NewsAdapter(CrimeRateReport.this,newsArticle);
                mRcNews.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Toast.makeText(CrimeRateReport.this,"ERROR",Toast.LENGTH_LONG);

            }
        });
    }

}
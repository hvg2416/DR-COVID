package com.developerhvg24.drcovid;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment{

    List<Trending_News_Data> articles;
    TextView total_articles_text_view;
    ProgressBar progressBar;
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null && networkInfo.isConnected()))
        {
            Toast.makeText(getContext(),"No Internet",Toast.LENGTH_SHORT).show();
        }
        else
        {
            getNewsJSONData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_news, container, false);
        total_articles_text_view = fragmentView.findViewById(R.id.total_articles_text_view);
        progressBar = fragmentView.findViewById(R.id.progress_bar_2);
        getNewsJSONData();
        return fragmentView;
    }

    private void getNewsJSONData() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Interface_Trending_NEWS_API.BASE_URL_NEWS_API)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Interface_Trending_NEWS_API trendingNewsApi = retrofit.create(Interface_Trending_NEWS_API.class);
        Call<Trending_News_Data_Main> call = trendingNewsApi.getNewsData();

        call.enqueue(new Callback<Trending_News_Data_Main>() {
            @Override
            public void onResponse(Call<Trending_News_Data_Main> call, Response<Trending_News_Data_Main> response) {
                progressBar.setVisibility(View.GONE);
                articles = response.body().getArticles();
                total_articles_text_view.setText("(" + Integer.toString(articles.size()) + " Articles)");
                //Log.d("DEBUG_MESSAGE",articles.toString());
                RecyclerView recyclerView = getView().findViewById(R.id.trending_news_recyler_view);
                TrendingNewsRecyclerViewAdapter recyclerViewAdapter = new TrendingNewsRecyclerViewAdapter(articles,getContext());
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<Trending_News_Data_Main> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //Log.d("DEBUG_MESSAGE : ",t.getMessage());
            }
        });
    }
}

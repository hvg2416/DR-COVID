package com.developerhvg24.drcovid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrendingNewsRecyclerViewAdapter extends RecyclerView.Adapter<TrendingNewsRecyclerViewAdapter.TrendingNewsRecyclerViewHolder> {

    private List<Trending_News_Data> trending_news_data;
    private Context context;
    public TrendingNewsRecyclerViewAdapter(List<Trending_News_Data> dataList,Context ctx) {
        this.trending_news_data = dataList;
        this.context = ctx;
    }

    @NonNull
    @Override
    public TrendingNewsRecyclerViewAdapter.TrendingNewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_item_trending_news_recyler_view_layout,parent,false);
        return new TrendingNewsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingNewsRecyclerViewAdapter.TrendingNewsRecyclerViewHolder holder, int position) {
        String pos = Integer.toString(position+1);
        holder.news_header_text_view.setText(pos + ". " +trending_news_data.get(position).getTitle());
        holder.news_desc_text_view.setText(trending_news_data.get(position).getDescription());
        holder.tap_to_know_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri url = Uri.parse(trending_news_data.get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW,url);
                if(intent.resolveActivity(context.getPackageManager())!=null)
                {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trending_news_data.size();
    }

    public class TrendingNewsRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView news_header_text_view,news_desc_text_view,tap_to_know_text_view;
        public TrendingNewsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            news_header_text_view = itemView.findViewById(R.id.trending_news_article_header_text_view);
            news_desc_text_view = itemView.findViewById(R.id.trending_news_article_description_text_view);
            tap_to_know_text_view = itemView.findViewById(R.id.tap_to_know_more_text_view);
        }
    }
}

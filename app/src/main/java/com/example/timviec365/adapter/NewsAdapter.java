package com.example.timviec365.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.model.DataCompanyNumberOne;
import com.example.timviec365.model.DataNews;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<DataNews> dataNewsList;
    Context context;

    public NewsAdapter(List<DataNews> dataNewsList) {

        this.dataNewsList = dataNewsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tintuc, viewGroup, false);
        context = viewGroup.getContext();
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataNews dataNews = dataNewsList.get(i);

        viewHolder.tvCongty.setText(dataNews.getTitle() +"");


        Picasso.get().load("https://timviec365.vn/ssl/upload/news/"+ dataNews.getImage()).into(viewHolder.postImg);

    }

    @Override
    public int getItemCount() {
        return dataNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvtime, tvDiadiem;
        private TextView tvCongty,tvSalary;
        private CardView cardItemRCV;
        private ImageView postImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postImg = (ImageView) itemView.findViewById(R.id.imgUv);
            tvCongty = (TextView) itemView.findViewById(R.id.tvTitle);
            tvtime = itemView.findViewById(R.id.tvTime);

            cardItemRCV = (CardView) itemView.findViewById(R.id.cardview);
        }

    }

    public void updateData(List<DataNews> dataNewsList) {
        this.dataNewsList.addAll(dataNewsList);
        notifyDataSetChanged();

    }




//    public void clearList() {
//        if (!this.exampleList.isEmpty())
//            this.exampleList.clear();
//    }
}

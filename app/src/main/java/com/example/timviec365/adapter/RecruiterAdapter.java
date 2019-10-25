package com.example.timviec365.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.model.Content;
import com.example.timviec365.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RecruiterAdapter extends RecyclerView.Adapter<RecruiterAdapter.ViewHolder> {
    List<Photo> exampleList;
    List<Content> ContentList;
    Context context;

    public RecruiterAdapter(List<Photo> exampleList) {

        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uv, viewGroup, false);
        context = viewGroup.getContext();
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Photo example = exampleList.get(i);



        if((i %3 ==0 )){
            viewHolder.tvTitle.setText(example.getTitle() +"");
            viewHolder.tvTitle.setTextColor(Color.RED);

        }else {
            viewHolder.tvTitle.setText(example.getTitle() +"");
            viewHolder.tvTitle.setTextColor(Color.BLACK);
        }

        Picasso.get().load(example.getUrlM()).into(viewHolder.postImg);



//        viewHolder.cardItemRCV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//
//                intent.putExtra("imgDetail",example.getUrlM()+ "");
////                intent.putExtra("title", example.getTitle().getRendered());
////                intent.putExtra("status", example.getStatus());
////                intent.putExtra("date", example.getDate());
////                intent.putExtra("dateGmt", example.getDateGmt());
////                intent.putExtra("author", example.getAuthor()+"");
////                intent.putExtra("featured_media", example.getFeaturedMedia()+"");
////                intent.putExtra("sticky", example.getSticky()+"");
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvType;
        private TextView tvStatus;
        private CardView cardItemRCV;
        private ImageView postImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postImg = (ImageView) itemView.findViewById(R.id.imgUv);
            tvTitle = (TextView) itemView.findViewById(R.id.tvName);

            cardItemRCV = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    public void updateData(List<Photo> exampleList) {
        this.exampleList.addAll(exampleList);
        notifyDataSetChanged();

    }

    public void allResutl(List<Photo> photoList) {
        this.exampleList.addAll(photoList);
        notifyDataSetChanged();

    }

//    public void clearList() {
//        if (!this.exampleList.isEmpty())
//            this.exampleList.clear();
//    }
}

package com.example.timviec365.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.activity.WebViewActivity;
import com.example.timviec365.model.DataCompany;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DataCompanyAdapter extends RecyclerView.Adapter<DataCompanyAdapter.ViewHolder> {
    List<DataCompany> dataCompanyList;
    Context context;

    public DataCompanyAdapter(List<DataCompany> dataCompanyList) {

        this.dataCompanyList = dataCompanyList;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final DataCompany dataCompany = dataCompanyList.get(i);



        viewHolder.tvTitle.setText(dataCompany.getTitle() +"");
        viewHolder.tvCongty.setText(dataCompany.getCompanyname() +"");
        viewHolder.tvDiadiem.setText(dataCompany.getProvince() +"");
        viewHolder.tvSalary.setText(dataCompany.getMucluong() +"");
        viewHolder.tvtime.setText(dataCompany.getNgaytao() +"");


        viewHolder.btnwebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",dataCompany.getUrl());
                context.startActivity(intent);
            }
        });




//then





        Picasso.get().load(dataCompany.getUrllogo()).into(viewHolder.postImg);

    }

    @Override
    public int getItemCount() {
        return dataCompanyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnwebview;
        private TextView tvTitle;
        private TextView tvtime, tvDiadiem;
        private TextView tvCongty,tvSalary;
        private CardView cardItemRCV;
        private ImageView postImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postImg = (ImageView) itemView.findViewById(R.id.imgUv);
            tvCongty = (TextView) itemView.findViewById(R.id.tvCongty);
            tvDiadiem = (TextView) itemView.findViewById(R.id.tvDiadiem);
            tvSalary = (TextView) itemView.findViewById(R.id.tvSalary);
            tvTitle = (TextView) itemView.findViewById(R.id.tvName);
            tvtime = itemView.findViewById(R.id.tvTime);

            btnwebview = itemView.findViewById(R.id.btnwebview);
        }

    }

    public void updateData(List<DataCompany> dataCompanyList) {
        this.dataCompanyList.addAll(dataCompanyList);
        notifyDataSetChanged();

    }


    public void clearList() {
        if (!this.dataCompanyList.isEmpty())
            this.dataCompanyList.clear();
    }
}

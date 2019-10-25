package com.example.timviec365.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.model.Datacolumn;
import com.example.timviec365.model.Photo;

import java.util.List;

public class DataColumnAdapter extends  RecyclerView.Adapter<DataColumnAdapter.ViewHolder> {
    private Context context;
    private List<Datacolumn> datacolumnList ;

    public DataColumnAdapter(List<Datacolumn> datacolumnList) {

        this.datacolumnList = datacolumnList;
    }
    @NonNull
    @Override
    public DataColumnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_uv, viewGroup, false);
        context = viewGroup.getContext();

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataColumnAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datacolumnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void updateData(List<Datacolumn> datacolumnList) {
        this.datacolumnList.addAll(datacolumnList);
        notifyDataSetChanged();

    }
}

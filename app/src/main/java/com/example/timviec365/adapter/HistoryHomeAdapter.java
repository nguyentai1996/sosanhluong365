package com.example.timviec365.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec365.R;
import com.example.timviec365.model.History;
import com.example.timviec365.splDAO.HistoryDAO;

import java.util.List;

public class HistoryHomeAdapter extends BaseAdapter {

    List<History> arrHis;
    public Context context;
    public LayoutInflater inflater;
    HistoryDAO historyDAO;


    public HistoryHomeAdapter(Context context, List<History> arrHis) {
        super();
        this.arrHis = arrHis;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        historyDAO = new HistoryDAO(context);
    }



    @Override
    public int getCount() {
        return arrHis.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_lv_home, null);
            holder.txtName = (TextView) convertView.findViewById(R.id.title);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.subTitle);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
            History _entry = (History) arrHis.get(position);

            holder.txtName.setText(_entry.getKey());
            holder.txtPhone.setText(_entry.getNameCity());
            return convertView;
        }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<History> items) {
        this.arrHis = items;
        notifyDataSetChanged();
    }

}

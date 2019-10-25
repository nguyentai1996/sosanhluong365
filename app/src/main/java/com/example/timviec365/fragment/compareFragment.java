package com.example.timviec365.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.timviec365.R;
import com.example.timviec365.adapter.DataCompanyNumberOneAdapter;
import com.example.timviec365.adapter.NewsAdapter;
import com.example.timviec365.config.JobRetrofit;
import com.example.timviec365.model.DataCompanyNumberOne;
import com.example.timviec365.model.DataNews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class compareFragment extends Fragment {
    private List<DataNews>  dataNewsList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rcvNews;
    private LinearLayout lnShare;
    private NewsAdapter adapterRCV;

    public compareFragment() {
        // Required empty public constructor
    }


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        rcvNews = (RecyclerView) view.findViewById(R.id.rcvTintuc);
        lnShare = (LinearLayout) view.findViewById(R.id.lnShare);

        lnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startShare();
            }
        });





        rcvNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rcvNews);
        adapterRCV = new NewsAdapter(dataNewsList);
        rcvNews.setHasFixedSize(true);
        rcvNews.setAdapter(adapterRCV);


        RetrofitgetNews();



// DUNG QUERY SAP SEP THU MUC MOI NHAT TRUOC


        return view;
    }

    private void RetrofitgetNews() {
        JobRetrofit.getInstance().getDataNews().enqueue(new Callback<List<DataNews>>() {
            @Override
            public void onResponse(Call<List<DataNews>> call, Response<List<DataNews>> response) {
                if (response.code() == 200 && response.body() != null) {
                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();



                } else {
                    Toast.makeText(getContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataNews>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }



    private void startShare() {
        ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setChooserTitle("Share URL")
                .setText("http://www.url.com")
                .startChooser();

    }

}

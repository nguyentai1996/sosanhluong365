package com.example.timviec365.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailSalaryComparison;
import com.example.timviec365.model.City;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnChartValueSelectedListener {
    private AutoCompleteTextView edNameJob;
    private Spinner spinner;
    private Button btnFind;
    private TextView tvCitysp;
    private ArrayList<City> cityBeansList = new ArrayList<>();
    private String postionSpinner;
    private String namecity;
    private Boolean selected;


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        spinner = view.findViewById(R.id.spinnerCity);
        btnFind = view.findViewById(R.id.btnFind);
        tvCitysp = view.findViewById(R.id.tvCitysp);
        edNameJob = view.findViewById(R.id.edNameJob);


        sipner();
        getAdress();


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = edNameJob.getText().toString();
                if (key.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner.equals("65")) {
                    Toast.makeText(getContext(), "Vui lòng chọn tỉnh thành", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), DetailSalaryComparison.class);
                    intent.putExtra("find", key);
                    intent.putExtra("pro", postionSpinner);
                    startActivity(intent);

                }
            }
        });

        tvCitysp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCitysp.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }


    private void getAdress() {
        ArrayList<String> itemArrayList = getNameJob("adress.json");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, R.id.tvSpiner, itemArrayList);
        edNameJob.setThreshold(0);
        edNameJob.setAdapter(adapter);

    }

    private void sipner() {

        City city = new City();

        City city1 = new City();
        city1.setNameCity("Toàn Quốc");
        city1.setIdCity("0");

        city.setNameCity("Không chọn");
        city.setIdCity("65");
        cityBeansList.add(city);
        cityBeansList.add(city1);
        cityBeansList.addAll(getCity("adress.json"));
        ArrayAdapter<City> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_layout, R.id.tvSpiner, cityBeansList);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                postionSpinner = cityBeansList.get(i).getIdCity();
                namecity = cityBeansList.get(i).getNameCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setAdapter(adapter);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    public ArrayList<City> getCity(String filename) {
        JSONObject jsonArray = null;

        ArrayList<City> cList = new ArrayList<>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 0; i < 65; i++) {
                    cList.add(new City(jsonArray.getJSONArray("db_city").getJSONObject(i).getString("cit_id"),
                            jsonArray.getJSONArray("db_city").getJSONObject(i).getString("cit_name")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cList;
    }

    public ArrayList<String> getNameJob(String filename) {
        JSONObject jsonArray = null;

        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 0; i < 1000; i++) {
                    cList.add(jsonArray.getJSONArray("db_category").getJSONObject(i).getString("cat_name"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cList;
    }


    private void setText() {
        String nameJob = "Việc làm";
        String text = "ở";
        String Adress = "Hà Nội";


        String textfinal = nameJob + text + Adress;
    }
}
package com.example.timviec365.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.adapter.DataCompanyAdapter;
import com.example.timviec365.config.JobRetrofit;
import com.example.timviec365.model.City;
import com.example.timviec365.model.DataCompany;
import com.example.timviec365.model.Datachart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSalaryComparison extends AppCompatActivity {
    private ArrayList<City> cityBeansList = new ArrayList<>();
    private String postionSpinner;
    private BarChart mChart;
    private Button btnFind;
    private Spinner spinner;
    private HorizontalBarChart horizontalBarChart;
    private AutoCompleteTextView edNameJob;
    private DataCompanyAdapter adapterRCV;
    private List<DataCompany> dataCompanyList;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rcv_company;
    private TextView tvCitysp, idNamejob, tvAdress, tvColleges, tvHighSchool, tvNoNeed, tvAfterUniversity, tvUniversity, tvOther;
    private String namecity = "", dataline1, dataline2, dataline3, dataline4, dataline5, dataline6, dataline7, dataline8, dataline9;
    private String dataExp1, dataExp2, dataExp3, dataExp4, dataExp5, dataExp6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_salary_comparison);

        mChart = findViewById(R.id.combinedChart);
        horizontalBarChart = findViewById(R.id.HorizontalBarChart);

        dataCompanyList = new ArrayList<>();

        adapterRCV = new DataCompanyAdapter(dataCompanyList);
        rcv_company = (RecyclerView) findViewById(R.id.rcv_company);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_company.setLayoutManager(linearLayoutManager);
        rcv_company.setHasFixedSize(true);
        rcv_company.setAdapter(adapterRCV);
        idNamejob = findViewById(R.id.idNamejob);
        tvAdress = findViewById(R.id.tvAdress);
        tvHighSchool = findViewById(R.id.tvHighSchool);
        tvNoNeed = findViewById(R.id.tvNoNeed);
        tvAfterUniversity = findViewById(R.id.tvAfterUniversity);
        tvUniversity = findViewById(R.id.tvUniversity);
        tvOther = findViewById(R.id.tvOther);
        tvColleges = findViewById(R.id.tvColleges);
        tvCitysp = findViewById(R.id.tvCitysp);
        edNameJob = findViewById(R.id.edNameJob);
        spinner = findViewById(R.id.spinnerCity);
        btnFind = findViewById(R.id.btnFind);


        RetrofitGetCompany();
        RetrofitGetData();

        sipner();
        getAdress();
        tvCitysp.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = edNameJob.getText().toString();
                if (key.equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner.equals("65")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn tỉnh thành", Toast.LENGTH_SHORT).show();
                } else {
                    RetrofitGetDataButtom();
                    RetrofitGetCompanyButtom();
                }
            }
        });



    }

    private void RetrofitGetDataButtom() {
        String findkey = edNameJob.getText().toString().trim();
        String province = postionSpinner;

        Map<String, String> map = new HashMap<>();

        map.put("findkey", findkey);
        map.put("province", String.valueOf(province));


        JobRetrofit.getInstance().getDatacolumn(map).enqueue(new Callback<Datachart>() {
            @Override
            public void onResponse(Call<Datachart> call, Response<Datachart> response) {
                if (response.code() == 200 && response.body() != null) {
//                    int data = response.body().getData().getRenderLuong().get(0).getValue();

                    Log.d("a", response.body().getKq().toString());
                    Datachart datachart = response.body();

                    if (datachart == null) {
                        Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {

                        datachart.getData().getRenderLuong().get(0).getValue();

                        tvNoNeed.setText(String.valueOf(datachart.getData().getRenderBangCap().get(0).getValue() + " việc"));
                        tvAfterUniversity.setText(String.valueOf(datachart.getData().getRenderBangCap().get(1).getValue() + " việc"));
                        tvUniversity.setText(String.valueOf(datachart.getData().getRenderBangCap().get(2).getValue() + " việc"));
                        tvColleges.setText(String.valueOf(datachart.getData().getRenderBangCap().get(3).getValue() + " việc"));
                        tvHighSchool.setText(String.valueOf(datachart.getData().getRenderBangCap().get(4).getValue() + " việc"));
                        tvOther.setText(String.valueOf(datachart.getData().getRenderBangCap().get(5).getValue() + " việc"));

                        dataline1 = String.valueOf(datachart.getData().getRenderLuong().get(0).getValue());
                        dataline2 = String.valueOf(datachart.getData().getRenderLuong().get(1).getValue());
                        dataline3 = String.valueOf(datachart.getData().getRenderLuong().get(2).getValue());
                        dataline4 = String.valueOf(datachart.getData().getRenderLuong().get(3).getValue());
                        dataline5 = String.valueOf(datachart.getData().getRenderLuong().get(4).getValue());
                        dataline6 = String.valueOf(datachart.getData().getRenderLuong().get(5).getValue());
                        dataline7 = String.valueOf(datachart.getData().getRenderLuong().get(6).getValue());
                        dataline8 = String.valueOf(datachart.getData().getRenderLuong().get(7).getValue());
                        dataline9 = String.valueOf(datachart.getData().getRenderLuong().get(8).getValue());


                        dataExp1 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(0).getValue());
                        dataExp2 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(1).getValue());
                        dataExp3 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(2).getValue());
                        dataExp4 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(3).getValue());
                        dataExp5 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(4).getValue());
                        dataExp6 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(5).getValue());


                        setDataBarChart();
                        barchart();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Datachart> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    private void RetrofitGetCompanyButtom() {

        String findkey = edNameJob.getText().toString().trim();
        String province = postionSpinner;
        Map<String, String> mapp = new HashMap<>();

        mapp.put("findkey", findkey);
        mapp.put("province", String.valueOf(province));


        JobRetrofit.getInstance().getDataCompany(mapp).enqueue(new Callback<List<DataCompany>>() {
            @Override
            public void onResponse(Call<List<DataCompany>> call, Response<List<DataCompany>> response) {
                if (response.code() == 200 && response.body() != null) {

                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();

                } else {
                    Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<DataCompany>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    private void RetrofitGetData() {
        String findkey = getIntent().getStringExtra("find");
        String province = getIntent().getStringExtra("pro");

        Map<String, String> map = new HashMap<>();

        map.put("findkey", findkey);
        map.put("province", String.valueOf(province));


        JobRetrofit.getInstance().getDatacolumn(map).enqueue(new Callback<Datachart>() {
            @Override
            public void onResponse(Call<Datachart> call, Response<Datachart> response) {
                if (response.code() == 200 && response.body() != null) {
//                    int data = response.body().getData().getRenderLuong().get(0).getValue();

                    Log.d("a", response.body().getKq().toString());
                    Datachart datachart = response.body();

                    if (datachart == null) {
                        Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {

                        datachart.getData().getRenderLuong().get(0).getValue();

                        tvNoNeed.setText(String.valueOf(datachart.getData().getRenderBangCap().get(0).getValue() + " việc"));
                        tvAfterUniversity.setText(String.valueOf(datachart.getData().getRenderBangCap().get(1).getValue() + " việc"));
                        tvUniversity.setText(String.valueOf(datachart.getData().getRenderBangCap().get(2).getValue() + " việc"));
                        tvColleges.setText(String.valueOf(datachart.getData().getRenderBangCap().get(3).getValue() + " việc"));
                        tvHighSchool.setText(String.valueOf(datachart.getData().getRenderBangCap().get(4).getValue() + " việc"));
                        tvOther.setText(String.valueOf(datachart.getData().getRenderBangCap().get(5).getValue() + " việc"));

                        dataline1 = String.valueOf(datachart.getData().getRenderLuong().get(0).getValue());
                        dataline2 = String.valueOf(datachart.getData().getRenderLuong().get(1).getValue());
                        dataline3 = String.valueOf(datachart.getData().getRenderLuong().get(2).getValue());
                        dataline4 = String.valueOf(datachart.getData().getRenderLuong().get(3).getValue());
                        dataline5 = String.valueOf(datachart.getData().getRenderLuong().get(4).getValue());
                        dataline6 = String.valueOf(datachart.getData().getRenderLuong().get(5).getValue());
                        dataline7 = String.valueOf(datachart.getData().getRenderLuong().get(6).getValue());
                        dataline8 = String.valueOf(datachart.getData().getRenderLuong().get(7).getValue());
                        dataline9 = String.valueOf(datachart.getData().getRenderLuong().get(8).getValue());


                        dataExp1 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(0).getValue());
                        dataExp2 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(1).getValue());
                        dataExp3 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(2).getValue());
                        dataExp4 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(3).getValue());
                        dataExp5 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(4).getValue());
                        dataExp6 = String.valueOf(datachart.getData().getRenderKinhNghiem().get(5).getValue());


                        setDataBarChart();
                        barchart();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Datachart> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    private void RetrofitGetCompany() {

        String findkey = getIntent().getStringExtra("find");
        String province = getIntent().getStringExtra("pro");
        Map<String, String> mapp = new HashMap<>();

        mapp.put("findkey", findkey);
        mapp.put("province", String.valueOf(province));


        JobRetrofit.getInstance().getDataCompany(mapp).enqueue(new Callback<List<DataCompany>>() {
            @Override
            public void onResponse(Call<List<DataCompany>> call, Response<List<DataCompany>> response) {
                if (response.code() == 200 && response.body() != null) {

                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();

                } else {
                    Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<DataCompany>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    private void barchart() {
        horizontalBarChart.getDescription().setEnabled(false);

        BarDataSet barDataSet = new BarDataSet(getData(), "Việc làm");
//        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(Color.YELLOW);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] exp = new String[]{"Hơn 10 năm kinh nghiệm", "5-10 năm kinh nghiệm", "2-5 năm kinh nghiệm", "1-2 năm kinh nghiệm", "0-1 năm kinh nghiệm", "Chưa có kinh nghiệm"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(exp);
        xAxis.setGranularity(0.5f);
        xAxis.setValueFormatter(formatter);
        horizontalBarChart.setData(barData);
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.animateXY(4000, 4000);


        horizontalBarChart.getXAxis().setDrawGridLines(false);
        horizontalBarChart.getXAxis().setDrawAxisLine(false);
        horizontalBarChart.getAxisLeft().setDrawGridLines(false);
        horizontalBarChart.getAxisRight().setDrawGridLines(false);
        mChart.setFitBars(true);
    }

    private ArrayList getData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, Float.parseFloat(dataExp6)));
        entries.add(new BarEntry(1, Float.parseFloat(dataExp5)));
        entries.add(new BarEntry(2, Float.parseFloat(dataExp4)));
        entries.add(new BarEntry(3, Float.parseFloat(dataExp3)));
        entries.add(new BarEntry(4, Float.parseFloat(dataExp2)));
        entries.add(new BarEntry(5, Float.parseFloat(dataExp1)));
        return entries;
    }

    private void setDataBarChart() {

        {
            ArrayList<BarEntry> yVals = new ArrayList<>();

            yVals.add(new BarEntry(0, Float.parseFloat(dataline1)));
            yVals.add(new BarEntry(1, Float.parseFloat(dataline2)));
            yVals.add(new BarEntry(2, Float.parseFloat(dataline3)));
            yVals.add(new BarEntry(3, Float.parseFloat(dataline4)));
            yVals.add(new BarEntry(4, Float.parseFloat(dataline5)));
            yVals.add(new BarEntry(5, Float.parseFloat(dataline6)));
            yVals.add(new BarEntry(6, Float.parseFloat(dataline7)));
            yVals.add(new BarEntry(7, Float.parseFloat(dataline8)));
            yVals.add(new BarEntry(8, Float.parseFloat(dataline9)));


            mChart.getDescription().setEnabled(false);
            BarDataSet set = new BarDataSet(yVals, "Việc làm");
//            set.setColors(new int[]  {Color.RED, Color.GREEN, Color.GRAY, Color.BLACK, Color.BLUE});
            set.setColors(ColorTemplate.MATERIAL_COLORS);
            final String[] exp = new String[]{"Thương lượng", "1-3tr", "3-5tr", "5-7tr", "7-10tr", "10-15tr", "15-20tr", "20-30tr", "30tr trở lên"};
            IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(exp);
            set.setDrawValues(true);
            XAxis xAxis = mChart.getXAxis();
            xAxis.setValueFormatter(formatter);
            xAxis.setDrawGridLines(false);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

            BarData barData = new BarData(set);
            mChart.setData(barData);
            mChart.setFitBars(true);
            mChart.animateY(2000);

        }
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

    private void getAdress() {
        ArrayList<String> itemArrayList = getNameJob("adress.json");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailSalaryComparison.this, R.layout.spinner_layout, R.id.tvSpiner, itemArrayList);
        edNameJob.setThreshold(0);
        edNameJob.setAdapter(adapter);

    }

    private void sipner() {

        City city1 = new City();
        city1.setNameCity("Toàn Quốc");
        city1.setIdCity("0");

        City city = new City();
        city.setNameCity("Không chọn");
        city.setIdCity("65");
        cityBeansList.add(city);
        cityBeansList.add(city1);
        cityBeansList.addAll(getCity("adress.json"));
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(DetailSalaryComparison.this, R.layout.spinner_layout, R.id.tvSpiner, cityBeansList);

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

}

package com.example.timviec365.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.adapter.DataCompanyNumberOneAdapter;
import com.example.timviec365.config.JobRetrofit;
import com.example.timviec365.fragmentDialog.FindMoreDialog;
import com.example.timviec365.fragmentDialog.LoadSearchSalaryDialog;
import com.example.timviec365.model.Career;
import com.example.timviec365.model.DataCompanyNumberOne;
import com.example.timviec365.model.DataSearchSalary;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

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


public class DetailSearchSalaryActivity extends AppCompatActivity  implements OnChartValueSelectedListener {

    private List<DataCompanyNumberOne> dataCompanyNumberOneList;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imgConectApp;
    private  int postionSpinner = -1;
    private RecyclerView rcvCompany;
    private DataCompanyNumberOneAdapter adapterRCV;
    private ArrayList<Career> careerArrayList = new ArrayList<>();
    private AutoCompleteTextView edCareerSearchSalary;

    private Button edFindSalary;
    private AutoCompleteTextView edNameJob, edLevel, edExperience, edform, edGender, edRank;
    private TextView nganhnghe,tvSalaryDown,tvSalaryDown2, tvSalarySearch, tvCareer, tvNameJob, tvAdress, tvLevel, tvExperience, tvform, tvGender, tvRank;
    private ImageView imgMore;

    private CombinedChart mChart;
    private String nameCity = "", findkey = "", postionCareer = "", postionCaree = "", career = "", nameCareer = "", nameCat = "", nganhnghechon = "", key = "";
    private float dataChart1, dataChart2, dataChart3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_detail_search_salary);


        edFindSalary = findViewById(R.id.edFindSalary);
        imgMore = findViewById(R.id.imgMore);
        tvSalaryDown = findViewById(R.id.tvSalaryDown);
        tvSalaryDown2 = findViewById(R.id.tvSalaryDown2);
        edNameJob = findViewById(R.id.edNameJobSearchSalary);
        tvAdress = findViewById(R.id.tvAdress);
        tvSalarySearch = findViewById(R.id.tvSalarySearch);
        tvExperience = findViewById(R.id.tvExprience);
        tvform = findViewById(R.id.tvForm);
        edCareerSearchSalary = findViewById(R.id.edCareerSearchSalary);
        tvRank = findViewById(R.id.tvRank);
        tvLevel = findViewById(R.id.tvLevel);
        tvGender = findViewById(R.id.tvGender);

        tvCareer = findViewById(R.id.Job);
        tvNameJob = findViewById(R.id.tvNamejob);
        imgConectApp = findViewById(R.id.imgConectApp);


        dataCompanyNumberOneList = new ArrayList<>();

        adapterRCV = new DataCompanyNumberOneAdapter(dataCompanyNumberOneList);
        rcvCompany = (RecyclerView) findViewById(R.id.rcv_ntd);
        linearLayoutManager = new LinearLayoutManager(DetailSearchSalaryActivity.this);
        rcvCompany.setLayoutManager(linearLayoutManager);
        rcvCompany.setHasFixedSize(true);
        rcvCompany.setAdapter(adapterRCV);


        mChart = (CombinedChart) findViewById(R.id.combinedChart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(DetailSearchSalaryActivity.this);

       setDataLine();


        init();
        addData();
        getDataCompanyNumberOne();

        postionCareer = getIntent().getStringExtra("career");
        nameCat = getIntent().getStringExtra("nameCat");
        edNameJob.setText(findkey);


//        spinerNameJob();
        spinerCareer();
        demoRetro();

        imgConectApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("vn.timviec365.myapplication");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keykey = edNameJob.getText().toString().trim();
                for (int i = 0; i < careerArrayList.size(); i++) {

                    Career career = careerArrayList.get(i);
                    if (career.getNameCat().toLowerCase().equalsIgnoreCase(edCareerSearchSalary.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                if (keykey.equals("")) {
                    Toast.makeText(DetailSearchSalaryActivity.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                }
                else if (postionSpinner == -1) {
                    Toast.makeText(DetailSearchSalaryActivity.this, "Ngành nghề bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                }else {
                    showAlertDialog(edNameJob.getText().toString().trim(), careerArrayList.get(postionSpinner).getIdCat(), careerArrayList.get(postionSpinner).getNameCat());
                Log.d("yeah",careerArrayList.get(postionSpinner).getNameCat());
                }
            }
        });


        edFindSalary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                key = edNameJob.getText().toString().trim();
                for (int i = 0; i < careerArrayList.size(); i++) {

                    Career career = careerArrayList.get(i);
                    if (career.getNameCat().toLowerCase().equalsIgnoreCase(edCareerSearchSalary.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                if (key.equals("")) {
                    Toast.makeText(DetailSearchSalaryActivity.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                }
                else if (postionSpinner == -1) {
                    Toast.makeText(DetailSearchSalaryActivity.this, "Ngành nghề bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(DetailSearchSalaryActivity.this, LoadSearchSalaryDialog.class);
                    intent.putExtra("key", key);
                    intent.putExtra("career", careerArrayList.get(postionSpinner).getIdCat());
                    intent.putExtra("nameCat", careerArrayList.get(postionSpinner).getNameCat());

                    Log.d("abc", String.valueOf(postionSpinner));
                    Log.d("abc", careerArrayList.get(postionSpinner).getNameCat());
                    startActivity(intent);

//                    demoRetroGetDataSearch();
//                    getDataCompanyNumberOne2();
//                    tvNameJob.setText(edNameJob.getText().toString().trim());
//                    tvCareer.setText(careerArrayList.get(postionSpinner).getNameCat());
//                    Log.d("ad", postionCaree);



                }
            }
        });

    }




    private void setDataLine() {
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("0");
        xLabel.add("Thấp nhất");
        xLabel.add("");
        xLabel.add("Trung bình");
        xLabel.add("");
        xLabel.add("Cao nhất");
        xLabel.add("0");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart());

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 1f);

        mChart.setData(data);
        mChart.invalidate();

    }

    private DataSet dataChart() {

        LineData d = new LineData();


        ArrayList<Entry> entries = new ArrayList<Entry>();



        entries.add(new Entry(1, (float) dataChart1));
        entries.add(new Entry(3, dataChart2));
        entries.add(new Entry(5, dataChart3));


        LineDataSet set = new LineDataSet(entries, "Lương theo tháng ( Tr VND)");
        set.setColor(Color.GREEN);
        set.setLineWidth(5f);
        set.setCircleColor(Color.RED);
        set.setCircleRadius(7f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(15f);
        set.setValueTextColor(Color.RED);
        mChart.setTouchEnabled(false);
        mChart.setDragEnabled(false);
        mChart.setPinchZoom(false);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return set;
    }


    private void addData() {

        nameCat = getIntent().getStringExtra("nameCat");
        career = getIntent().getStringExtra("career");
        findkey = getIntent().getStringExtra("key");
        tvNameJob.setText(findkey);
        tvCareer.setText(nameCat);
        tvAdress.setText("KHÔNG CHỌN");
        tvLevel.setText("KHÔNG CHỌN");
        tvExperience.setText("KHÔNG CHỌN");
        tvGender.setText("KHÔNG CHỌN");
        tvRank.setText("KHÔNG CHỌN");
        tvform.setText("KHÔNG CHỌN");
    }

    private void init() {

    }

    private void showAlertDialog(String key, String postionCareer, String nameCat) {
        postionCareer = careerArrayList.get(postionSpinner).getIdCat();
        nameCat = careerArrayList.get(postionSpinner).getNameCat();
        FragmentManager fm = getSupportFragmentManager();
        FindMoreDialog alertDialog = FindMoreDialog.newInstance(key, postionCareer, nameCat);
        alertDialog.show(fm, "fragment_alert");
    }




    public void demoRetro() {

        findkey = getIntent().getStringExtra("key");
        String nganhnghe = getIntent().getStringExtra("career");

        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("nganhnghe", nganhnghe);
//        mapm.put("kinhnghiem", positionExpx);

        JobRetrofit.getInstance().getDataSearchSalary(mapm).enqueue(new Callback<DataSearchSalary>() {
            @Override
            public void onResponse(Call<DataSearchSalary> call, Response<DataSearchSalary> response) {
                if (response.code() == 200 && response.body() != null) {
                    DataSearchSalary dataSearchSalary = response.body();

                    if (dataSearchSalary.getData() == null) {
                        Toast.makeText(DetailSearchSalaryActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        dataSearchSalary.getData().get(0).getY();
                        tvSalarySearch.setText(dataSearchSalary.getData().get(1).getIndexLabel() + "\n" + "Nghìn");
                        tvSalaryDown.setText(String.valueOf(dataSearchSalary.getData().get(1).getIndexLabel()) );
                        tvSalaryDown2.setText(String.valueOf(dataSearchSalary.getData().get(1).getIndexLabel()) + " Nghìn" );
                        tvSalaryDown2.setTextColor(getResources().getColor(R.color.colorYellow));
                        dataChart1 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(0).getY()));
                        dataChart2 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(1).getY()));
                        dataChart3 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(2).getY()));

                        dataChart();
                        setDataLine();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataSearchSalary> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void getDataCompanyNumberOne() {

        career = getIntent().getStringExtra("career");
        findkey = getIntent().getStringExtra("key");

        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("bangcap", String.valueOf(0));
        mapm.put("nganhnghechon", career);
        mapm.put("kinhnghiem", String.valueOf(0));
        mapm.put("hinhthuclamviec", String.valueOf(0));
        mapm.put("gioitinh", String.valueOf(0));
        mapm.put("capbac", String.valueOf(0));
        mapm.put("tinhthanh", String.valueOf(0));


        JobRetrofit.getInstance().getDataCompanyNumberOne(mapm).enqueue(new Callback<List<DataCompanyNumberOne>>() {
            @Override
            public void onResponse(Call<List<DataCompanyNumberOne>> call, Response<List<DataCompanyNumberOne>> response) {
                if (response.code() == 200 && response.body() != null) {
                    adapterRCV.clearList();
                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<DataCompanyNumberOne>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }


    public void getDataCompanyNumberOne2() {

        String fikey = edNameJob.getText().toString().trim();
        String nganhnghe = careerArrayList.get(postionSpinner).getIdCat();

        Map<String, String> mapm = new HashMap<>();


        mapm.put("tinhthanh", String.valueOf(0));
        mapm.put("nganhnghechon", nganhnghe);
        mapm.put("findkey", fikey);


        JobRetrofit.getInstance().getDataCompanyNumberOne(mapm).enqueue(new Callback<List<DataCompanyNumberOne>>() {
            @Override
            public void onResponse(Call<List<DataCompanyNumberOne>> call, Response<List<DataCompanyNumberOne>> response) {
                if (response.code() == 200 && response.body() != null) {
                    adapterRCV.clearList();
                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DataCompanyNumberOne>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public ArrayList<String> getNameJob(String filename1) {
        JSONObject jsonArray = null;

        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename1);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 0; i < 100; i++) {
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



    public void demoRetroGetDataSearch() {
        final String findkey = edNameJob.getText().toString();
        String nganhnghe = careerArrayList.get(postionSpinner).getIdCat();

        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("nganhnghe", nganhnghe);


        JobRetrofit.getInstance().getDataSearchSalary(mapm).enqueue(new Callback<DataSearchSalary>() {
            @Override
            public void onResponse(Call<DataSearchSalary> call, Response<DataSearchSalary> response) {
                if (response.code() == 200 && response.body() != null) {
                    DataSearchSalary dataSearchSalary = response.body();

                    dataSearchSalary.getData().get(0).getY();
                    tvSalarySearch.setText(dataSearchSalary.getData().get(1).getIndexLabel() + "Nghìn");
                    tvSalaryDown.setText(dataSearchSalary.getData().get(1).getIndexLabel() + " Nghìn");
                    tvSalaryDown2.setText(dataSearchSalary.getData().get(1).getIndexLabel() + " Nghìn");
                    dataChart1 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(0).getY()));
                    dataChart2 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(1).getY()));
                    dataChart3 = Float.parseFloat(String.valueOf(dataSearchSalary.getData().get(2).getY()));

                    dataChart();
                    setDataLine();
                }
            }

            @Override
            public void onFailure(Call<DataSearchSalary> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }


    public ArrayList<Career> getDataCeareer(String filename2) {
        JSONObject jsonArray = null;

        ArrayList<Career> cListCareer = new ArrayList<>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename2);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cListCareer.add(new Career(jsonArray.getJSONArray("db_category").getJSONObject(i).getString("cat_id"),
                            jsonArray.getJSONArray("db_category").getJSONObject(i).getString("cat_name")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cListCareer;
    }

    private void spinerCareer() {

        Career career1 = new Career();
        career1.setNameCat("Không chọn");
        career1.setIdCat("-1");
        careerArrayList.add(career1);

        Career career = new Career();
        career.setNameCat("Không yêu cầu");
        career.setIdCat("0");
        careerArrayList.add(career);

        careerArrayList.addAll(getDataCeareer("adress.json"));
        ArrayAdapter<Career> adapter = new ArrayAdapter<Career>(DetailSearchSalaryActivity.this, R.layout.spinner_layout, R.id.tvSpiner, careerArrayList);

        edCareerSearchSalary.setAdapter(adapter);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

package com.example.timviec365.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.adapter.DataCompanyAdapter;
import com.example.timviec365.adapter.DataCompanyNumberOneAdapter;
import com.example.timviec365.config.JobRetrofit;
import com.example.timviec365.fragmentDialog.FindMoreDialog;
import com.example.timviec365.model.Career;
import com.example.timviec365.model.DataCompany;
import com.example.timviec365.model.DataCompanyNumberOne;
import com.example.timviec365.model.DataNews;
import com.example.timviec365.model.DataSearchSalary;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

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

public class DetailDialogSearchSalary extends AppCompatActivity {

    private List<DataCompanyNumberOne> dataCompanyNumberOneList;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rcvCompany;
    private DataCompanyNumberOneAdapter adapterRCV;


    private ArrayList<Career> careerArrayList = new ArrayList<>();
    private Spinner spCareer;

    private Button edFindSalary;
    private AutoCompleteTextView edNameJob, edLevel, edExperience, edform, edGender, edRank;
    private TextView career,tvSalarySearch, tvCareer, tvNameJob, tvAdress, tvLevel, tvExperience, tvform, tvGender, tvRank;
    private ImageView imgMore;

    private String getNganhnghe= "",City = "",Form = "",Level= "",Gender= "",Rank= "",Exp = "";
    private BarChart mChart;
    private String postionCareer, nameCareer= "", findkey, nganhnghe, positionCityx, positionFormx, positionLevelx, positionExpx, positionGenderx, positionRankx;
    private String dataChart1, dataChart2, dataChart3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog_search_salary);

        init();
        getInten();
        setTitel();
        spinerCareer();
        demoRetro();
        getDataCompanyNumberOne();

        career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                career.setVisibility(View.GONE);
                spCareer.setVisibility(View.VISIBLE);
            }
        });
        mChart.setFitBars(true);

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findkey.isEmpty()) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (nameCareer.equals("Không chọn")) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng chọn ngành nghề", Toast.LENGTH_SHORT).show();
                }else {
                    showAlertDialog(edNameJob.getText().toString().trim(), postionCareer);
                }
            }
        });


        edFindSalary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (findkey.isEmpty()) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (nameCareer.equals("Không chọn")) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng chọn ngành nghề", Toast.LENGTH_SHORT).show();
                } else {
                    demoRetroGetDataSearch();
                    tvNameJob.setText(findkey);
                    tvAdress.setText("KHÔNG CHỌN");
                    tvLevel.setText("KHÔNG CHỌN");
                    tvExperience.setText("KHÔNG CHỌN");
                    tvCareer.setText(nganhnghe);
                    tvGender.setText("KHÔNG CHỌN");
                    tvRank.setText("KHÔNG CHỌN");
                    tvform.setText("KHÔNG CHỌN");
                }
            }
        });

    }

    private void setTitel() {
        tvNameJob.setText(findkey);
        tvAdress.setText(City);
        tvLevel.setText(Level);
        tvExperience.setText(Exp);
        tvCareer.setText(nganhnghe);
        tvGender.setText(Gender);
        tvRank.setText(Rank);
        tvform.setText(Form);
        edNameJob.setText(findkey);
    }

    private void getInten() {
        findkey = getIntent().getStringExtra("findkey");
        nganhnghe = getIntent().getStringExtra("career");
        positionCityx = getIntent().getStringExtra("positionCityx");
        positionFormx = getIntent().getStringExtra("positionFormx");
        positionExpx = getIntent().getStringExtra("positionExpx");
        positionLevelx = getIntent().getStringExtra("positionLevelx");
        positionGenderx = getIntent().getStringExtra("positionGenderx");
        positionRankx = getIntent().getStringExtra("positionRankx");

        City = getIntent().getStringExtra("positionCity");
        Form = getIntent().getStringExtra("positionForm");
        Exp = getIntent().getStringExtra("positionExp");
        Level = getIntent().getStringExtra("positionLevel");
        Gender = getIntent().getStringExtra("positionGender");
        Rank = getIntent().getStringExtra("positionRank");


    }

    private void init() {
        mChart = (BarChart) findViewById(R.id.combinedChart);
        career = findViewById(R.id.nganhnghe);
        edFindSalary = findViewById(R.id.edFindSalary);
        imgMore = findViewById(R.id.imgMore);
        edNameJob = findViewById(R.id.edNameJobSearchSalary);
        tvAdress = findViewById(R.id.tvAdress);
        tvSalarySearch = findViewById(R.id.tvSalarySearch);
        tvExperience = findViewById(R.id.tvExprience);
        tvform = findViewById(R.id.tvForm);
        tvCareer = findViewById(R.id.tvCareer);
        spCareer = findViewById(R.id.SpCareerSearchSalary);
        tvRank = findViewById(R.id.tvRank);
        tvLevel = findViewById(R.id.tvLevel);
        tvGender = findViewById(R.id.tvGender);
        tvNameJob = findViewById(R.id.tvNamejob);


        dataCompanyNumberOneList = new ArrayList<>();

        adapterRCV = new DataCompanyNumberOneAdapter(dataCompanyNumberOneList);
        rcvCompany = (RecyclerView) findViewById(R.id.rcv_ntd);
        linearLayoutManager = new LinearLayoutManager(DetailDialogSearchSalary.this);
        rcvCompany.setLayoutManager(linearLayoutManager);
        rcvCompany.setHasFixedSize(true);
        rcvCompany.setAdapter(adapterRCV);
    }

    private void showAlertDialog(String key, String postionCareer) {
        FragmentManager fm = getSupportFragmentManager();
        FindMoreDialog alertDialog = FindMoreDialog.newInstance(key,postionCareer);
        alertDialog.show(fm, "fragment_alert");
    }

    private void setDataBarChart() {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        yVals.add(new BarEntry(0, Float.parseFloat(dataChart1)));
        yVals.add(new BarEntry(1, Float.parseFloat(dataChart2)));
        yVals.add(new BarEntry(2, Float.parseFloat(dataChart3)));

        mChart.getDescription().setEnabled(false);
        BarDataSet set = new BarDataSet(yVals, "data set1");
        set.setColor(Color.YELLOW);
        final String[] exp = new String[]{"Thấp nhất", "Trung bình", "Cao nhất"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(exp);
        set.setDrawValues(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        BarData barData = new BarData(set);
        mChart.setData(barData);
        mChart.invalidate();
        mChart.setFitBars(true);
        mChart.animateY(500);


    }

    public void demoRetro() {

        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("bangcap", positionLevelx);
        mapm.put("nganhnghe", nganhnghe);
        mapm.put("kinhnghiem", positionExpx);
        mapm.put("hinhthuclamviec", positionFormx);
        mapm.put("gioitinh", positionGenderx);
        mapm.put("capbac", positionRankx);
        mapm.put("noilamviec", positionCityx);


        JobRetrofit.getInstance().getDataSearchSalary(mapm).enqueue(new Callback<DataSearchSalary>() {
            @Override
            public void onResponse(Call<DataSearchSalary> call, Response<DataSearchSalary> response) {
                if (response.code() == 200 && response.body() != null) {
                    DataSearchSalary dataSearchSalary = response.body();
                    if (findkey == null) {
                        Toast.makeText(DetailDialogSearchSalary.this, "không có dữ liệu vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    }
                    if (dataSearchSalary.getData() == null) {
                        Toast.makeText(DetailDialogSearchSalary.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {

                        dataSearchSalary.getData().get(0).getY();
                        tvSalarySearch.setText(dataSearchSalary.getData().get(1).getIndexLabel() + "\n" + "Nghìn");
                        dataChart1 = String.valueOf(dataSearchSalary.getData().get(0).getY());
                        dataChart2 = String.valueOf(dataSearchSalary.getData().get(1).getY());
                        dataChart3 = String.valueOf(dataSearchSalary.getData().get(2).getY());

                        Log.d("azz", findkey);
                        Log.d("azz", nganhnghe);
                        Log.d("azz", positionCityx);
                        Log.d("azz", positionExpx);
                        Log.d("azz", positionFormx);
                        Log.d("azz", positionGenderx);
                        Log.d("azz", positionRankx);
                        setDataBarChart();
                    }
                } else {
                    Toast.makeText(DetailDialogSearchSalary.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataSearchSalary> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }

    public void demoRetroGetDataSearch() {


        String findkey = edNameJob.getText().toString();
        String nganhnghe = postionCareer;


        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("nganhnghe", nganhnghe);


        JobRetrofit.getInstance().getDataSearchSalary(mapm).enqueue(new Callback<DataSearchSalary>() {
            @Override
            public void onResponse(Call<DataSearchSalary> call, Response<DataSearchSalary> response) {
                if (response.code() == 200 && response.body() != null) {
                    DataSearchSalary dataSearchSalary = response.body();

                    if (dataSearchSalary.getData() == null) {
                        Toast.makeText(DetailDialogSearchSalary.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        dataSearchSalary.getData().get(0).getY();
                        tvSalarySearch.setText(dataSearchSalary.getData().get(1).getIndexLabel() + "\n" + "Nghìn");
                        dataChart1 = String.valueOf(dataSearchSalary.getData().get(0).getY());
                        dataChart2 = String.valueOf(dataSearchSalary.getData().get(1).getY());
                        dataChart3 = String.valueOf(dataSearchSalary.getData().get(2).getY());

                        setDataBarChart();
                    }
                } else {
                    Toast.makeText(DetailDialogSearchSalary.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataSearchSalary> call, Throwable t) {
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
        ArrayAdapter<Career> adapter = new ArrayAdapter<Career>(DetailDialogSearchSalary.this, R.layout.spinner_layout,R.id.tvSpiner, careerArrayList);
        spCareer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                postionCareer = careerArrayList.get(i).getIdCat();
                nameCareer = careerArrayList.get(i).getNameCat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spCareer.setAdapter(adapter);
    }



    public void getDataCompanyNumberOne() {

        Map<String, String> mapm = new HashMap<>();

        mapm.put("tinhthanh", positionCityx );
        mapm.put("nganhnghechon", nganhnghe);
        mapm.put("findkey", findkey);

        JobRetrofit.getInstance().getDataCompanyNumberOne(mapm).enqueue(new Callback<List<DataCompanyNumberOne>>() {
            @Override
            public void onResponse(Call<List<DataCompanyNumberOne>> call, Response<List<DataCompanyNumberOne>> response) {
                if (response.code() == 200 && response.body() != null) {
                    adapterRCV.updateData(response.body());
                    adapterRCV.notifyDataSetChanged();


                } else {
                    Toast.makeText(DetailDialogSearchSalary.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataCompanyNumberOne>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });
    }
}

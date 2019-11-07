package com.example.timviec365.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
    private AutoCompleteTextView edNameJob, edLevel, edExperience, edform, edGender, edRank, edCareerSearchSalary;
    private TextView tvSalaryDown,tvSalaryDown2,career, tvSalarySearch, tvCareer, tvNameJob, tvAdress, tvLevel, tvExperience, tvform, tvGender, tvRank;
    private ImageView imgMore;
    private float dataChart1, dataChart2, dataChart3;

    private int postionSpinner = -1;
    private String getNganhnghe = "", City = "", Form = "", Level = "", Gender = "", Rank = "", Exp = "";
    private CombinedChart mChart;
    private String nameCat = "", nameCity = "", postionCareer, nameCareer = "", findkey, ponganhnghe, positionCityx, positionFormx = "", positionLevelx = "", positionExpx = "", positionGenderx = "", positionRankx = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog_search_salary);

        init();
        getInten();
        setTitel();
        spinerCareer();
        setDataLine();
        demoRetro();
        getDataCompanyNumberOne();


        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < careerArrayList.size(); i++) {

                    Career career = careerArrayList.get(i);
                    if (career.getNameCat().toLowerCase().equalsIgnoreCase(edCareerSearchSalary.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                if (findkey.equals("")) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner == -1) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Ngành nghề bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    showAlertDialog(edNameJob.getText().toString().trim(), careerArrayList.get(postionSpinner).getIdCat(), careerArrayList.get(postionSpinner).getNameCat());
                    Log.d("yeah", careerArrayList.get(postionSpinner).getIdCat());
                }
            }
        });


        edFindSalary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (findkey.isEmpty()) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < careerArrayList.size(); i++) {

                    Career career = careerArrayList.get(i);
                    if (career.getNameCat().toLowerCase().equalsIgnoreCase(edCareerSearchSalary.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                if (findkey.equals("")) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner == -1) {
                    Toast.makeText(DetailDialogSearchSalary.this, "Ngành nghề bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DetailDialogSearchSalary.this, LoadSearchSalaryDialog.class);
                    intent.putExtra("key", findkey);
                    intent.putExtra("career", careerArrayList.get(postionSpinner).getIdCat());
                    intent.putExtra("nameCat", careerArrayList.get(postionSpinner).getNameCat());

                    Log.d("abc", String.valueOf(postionSpinner));
                    Log.d("abc", careerArrayList.get(postionSpinner).getNameCat());
                    startActivity(intent);

//                    demoRetroGetDataSearch();
                    tvNameJob.setText(findkey);
                    tvAdress.setText("KHÔNG CHỌN");
                    tvLevel.setText("KHÔNG CHỌN");
                    tvExperience.setText("KHÔNG CHỌN");
                    tvCareer.setText(ponganhnghe);
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
        tvCareer.setText(nameCat);
        tvGender.setText(Gender);
        tvRank.setText(Rank);
        tvform.setText(Form);
        edNameJob.setText(findkey);
    }

    private void getInten() {

        findkey = getIntent().getStringExtra("findkey");
        ponganhnghe = getIntent().getStringExtra("career");
        positionCityx = getIntent().getStringExtra("positionCityx");
        nameCat = getIntent().getStringExtra("nameCat");
        Rank = getIntent().getStringExtra("positionRank");
        Gender = getIntent().getStringExtra("positionGender");
        Form = getIntent().getStringExtra("positionForm");
        City = getIntent().getStringExtra("positionCity");
        Exp = getIntent().getStringExtra("positionExp");
        Level = getIntent().getStringExtra("positionLevel");


    }


    private void init() {
        mChart = findViewById(R.id.combinedChart);
        edFindSalary = findViewById(R.id.edFindSalary);
        imgMore = findViewById(R.id.imgMore);
        tvSalaryDown = findViewById(R.id.tvSalaryDown);
        tvSalaryDown2 = findViewById(R.id.tvSalaryDown2);
        edNameJob = findViewById(R.id.edNameJobSearchSalary);
        tvAdress = findViewById(R.id.tvAdress);
        tvSalarySearch = findViewById(R.id.tvSalarySearch);
        tvExperience = findViewById(R.id.tvExprience);
        tvform = findViewById(R.id.tvForm);
        tvCareer = findViewById(R.id.tvCareer);
        edCareerSearchSalary = findViewById(R.id.edCareerSearchSalary);
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

    private void showAlertDialog(String key, String postionCareer, String nameCat) {
        postionCareer = careerArrayList.get(postionSpinner).getIdCat();
        nameCat = careerArrayList.get(postionSpinner).getNameCat();
        FragmentManager fm = getSupportFragmentManager();
        FindMoreDialog alertDialog = FindMoreDialog.newInstance(key, postionCareer, nameCat);
        alertDialog.show(fm, "fragment_alert");
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


    public void demoRetro() {
        positionExpx = getIntent().getStringExtra("positionExpx");
        positionCityx = getIntent().getStringExtra("positionCityx");
        positionLevelx = getIntent().getStringExtra("positionLevelx");
        positionFormx = getIntent().getStringExtra("positionFormx");
        positionGenderx = getIntent().getStringExtra("positionGenderx");
        positionRankx = getIntent().getStringExtra("positionRankx");
        findkey = getIntent().getStringExtra("findkey");
        nameCat = getIntent().getStringExtra("nameCat");

        Map<String, String> mapm = new HashMap<>();

        mapm.put("findkey", findkey);
        mapm.put("nganhnghe", ponganhnghe);
        mapm.put("noilamviec", positionCityx);
        mapm.put("hinhthuclamviec", positionFormx.equals("0") ? "" : positionFormx);
        mapm.put("kinhnghiem", positionExpx.equals("0") ? "" : positionExpx);
        mapm.put("bangcap", positionLevelx.equals("0") ? "" : positionLevelx);
        mapm.put("gioitinh", positionGenderx.equals("0") ? "" : positionGenderx);
        mapm.put("capbac", positionRankx.equals("0") ? "" : positionRankx);


        JobRetrofit.getInstance().getDataSearchSalary(mapm).enqueue(new Callback<DataSearchSalary>() {
            @Override
            public void onResponse(Call<DataSearchSalary> call, Response<DataSearchSalary> response) {
                if (response.code() == 200 && response.body() != null) {
                    DataSearchSalary dataSearchSalary = response.body();


                    dataSearchSalary.getData().get(0).getY();
                    tvSalarySearch.setText(dataSearchSalary.getData().get(1).getIndexLabel() + "\n" + "Nghìn");
                    tvSalaryDown.setText(dataSearchSalary.getData().get(1).getIndexLabel() + " Nghìn");
                    tvSalaryDown2.setText(dataSearchSalary.getData().get(1).getIndexLabel()  + " Nghìn");
                    tvSalaryDown2.setTextColor(getResources().getColor(R.color.colorYellow));
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

        Career career = new Career();
        career.setNameCat("Không yêu cầu");
        career.setIdCat("0");
        careerArrayList.add(career);

        careerArrayList.addAll(getDataCeareer("adress.json"));
        ArrayAdapter<Career> adapter = new ArrayAdapter<Career>(DetailDialogSearchSalary.this, R.layout.spinner_layout, R.id.tvSpiner, careerArrayList);

        edCareerSearchSalary.setAdapter(adapter);
    }


    public void getDataCompanyNumberOne() {

        Map<String, String> mapm = new HashMap<>();

        mapm.put("tinhthanh", positionCityx);
        mapm.put("nganhnghechon", ponganhnghe);
        mapm.put("findkey", findkey);

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
}

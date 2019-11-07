package com.example.timviec365.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec365.R;
import com.example.timviec365.adapter.DataCompanyAdapter;
import com.example.timviec365.adapter.HistoryHomeAdapter;
import com.example.timviec365.config.JobRetrofit;
import com.example.timviec365.fragmentDialog.LoadHomeDialog;
import com.example.timviec365.model.City;
import com.example.timviec365.model.DataCompany;
import com.example.timviec365.model.Datachart;
import com.example.timviec365.model.History;
import com.example.timviec365.splDAO.HistoryDAO;
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
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSalaryComparison extends AppCompatActivity {
    private static int MAX_LENGTH = 100;
    private ArrayList<City> cityBeansList = new ArrayList<>();
    private int postionSpinner = -1,countcompany = 0;
    private String province = "";
    private BarChart mChart;
    private ImageView imgConectApp;
    private HistoryDAO historyDAO;
    private Button btnFind;
    private AutoCompleteTextView edCityx;
    private HorizontalBarChart horizontalBarChart;
    private AutoCompleteTextView edNameJob;
    private DataCompanyAdapter adapterRCV;
    private List<DataCompany> dataCompanyList;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rcv_company;
    private TextView tv4, tvnJ, namename, idNamejob, idSite, tvnameJob, tvAdress, tvColleges, tvHighSchool, tvNoNeed, tvAfterUniversity, tvUniversity, tvOther;
    private String key = "", dataline1, dataline2, dataline3, dataline4, dataline5, dataline6, dataline7, dataline8, dataline9;
    private String dataExp1, dataExp2, dataExp3, dataExp4, dataExp5, dataExp6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_detail_salary_comparison);
        setupUI(findViewById(R.id.parent));


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
        imgConectApp = findViewById(R.id.imgConectApp);
        tvAdress = findViewById(R.id.tvAdress);
        tv4 = findViewById(R.id.tv4);
        tvnJ = findViewById(R.id.tvnJ);
        namename = findViewById(R.id.namename);
        idSite = findViewById(R.id.idSite);
        tvnameJob = findViewById(R.id.tvnameJob);
        tvAdress = findViewById(R.id.tvAdress);
        tvHighSchool = findViewById(R.id.tvHighSchool);
        tvNoNeed = findViewById(R.id.tvNoNeed);
        tvAfterUniversity = findViewById(R.id.tvAfterUniversity);
        tvUniversity = findViewById(R.id.tvUniversity);
        tvOther = findViewById(R.id.tvOther);
        tvColleges = findViewById(R.id.tvColleges);
        edNameJob = findViewById(R.id.edNameJob);
        edCityx = findViewById(R.id.edCityx);
        btnFind = findViewById(R.id.btnFind);

        RetrofitGetCompany();
        RetrofitGetData();
        sipner();
        getAdress();


        imgConectApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("vn.timviec365.myapplication");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = edNameJob.getText().toString().trim();
                for (int i = 0; i < cityBeansList.size(); i++) {

                    City city = cityBeansList.get(i);
                    if (city.getNameCity().toLowerCase().equalsIgnoreCase(edCityx.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                if (key.equals("")) {
                    Toast.makeText(DetailSalaryComparison.this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner == -1) {
                    Toast.makeText(DetailSalaryComparison.this, "Địa điểm bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    History history = new History(random(), edNameJob.getText().toString().trim(), cityBeansList.get(postionSpinner).getIdCity(), edCityx.getText().toString().trim());
                    if (historyDAO.insertHistory(history) > 0) {
                        Intent intent = new Intent(DetailSalaryComparison.this, LoadHomeDialog.class);
                        intent.putExtra("find", key);
                        intent.putExtra("pro", cityBeansList.get(postionSpinner).getIdCity());
                        intent.putExtra("namecity", cityBeansList.get(postionSpinner).getNameCity());
                        Log.d("zz", cityBeansList.get(postionSpinner).getNameCity());
                        startActivity(intent);
                    } else {
                        Toast.makeText(DetailSalaryComparison.this, "Thêm dữ liệu vào lịch sử thất bại",
                                Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(DetailSalaryComparison.this, LoadHomeDialog.class);
                    intent.putExtra("find", key);
                    intent.putExtra("pro", cityBeansList.get(postionSpinner).getIdCity());
                    intent.putExtra("namecity", cityBeansList.get(postionSpinner).getNameCity());
                    Log.d("zz", cityBeansList.get(postionSpinner).getNameCity());
                    startActivity(intent);
//                    customTextButtom();
//                    RetrofitGetDataButtom();
//                    RetrofitGetCompanyButtom();


                }
            }
        });


    }

    private void customText() {
        String note1 = "Các công ty tuyển dụng ";
        String note2 = getIntent().getStringExtra("find");
        String note4 = getIntent().getStringExtra("namecity");
        String note3 = " ở ";
        String hienco0 = " Việc làm ";
        String hienco = " hiện có ";
        String hienco1 = String.valueOf(Integer.parseInt(dataExp1)+ Integer.parseInt(dataExp2) + Integer.parseInt(dataExp3) + Integer.parseInt(dataExp4) + Integer.parseInt(dataExp5));;
        String site = "Dữ liệu được tổng hợp tại ";
        String site0 = "Hơn 100 site ";
        String site1 = "tìm việc uy tín trên cả nước, tính chính xác yêu cầu của bạn.";
        String cothe = "Bạn có thể làm công việc ";
        String hienco2 = " công ty đang tuyển dụng thuộc danh mục ";
        String note5 = " với mức lương trung bình từ ";
        String note6 = " 1- 3tr ";
        String note = note2 + note3 + note4;
        String note7 = "tùy vào từng vị trí và yêu cầu nhà tuyển dụng đưa ra. Bạn nên chọn công việc ";
        String note8 = ". Tại những công ty có mức lương cao tương ứng với năng lực trình độ của bạn để đảm bảo quyền lợi và công sức bạn bỏ ra. ";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString text1 = new SpannableString(note1);
        text1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note1.length() - 1, 0);

        builder.append(text1);

        SpannableString text2 = new SpannableString(note);
        text2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        text2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder.append(text2);


        SpannableStringBuilder builder1 = new SpannableStringBuilder();
        SpannableStringBuilder builderx = new SpannableStringBuilder();
        SpannableStringBuilder builderSite = new SpannableStringBuilder();
        SpannableStringBuilder builderName = new SpannableStringBuilder();

        SpannableString namebuild = new SpannableString(note2);
        namebuild.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), 0, note2.length(), 0);
        builderName.append(namebuild);

        SpannableString textKey0 = new SpannableString(hienco0);
        textKey0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco0.length(), 0);
        builder1.append(textKey0);

        SpannableString sitesite = new SpannableString(site);
        sitesite.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, site.length(), 0);
        builderSite.append(sitesite);

        SpannableString sitesite0 = new SpannableString(site0);
        sitesite0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, site0.length(), 0);
        sitesite0.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, site0.length(), 0);
        builderSite.append(sitesite0);
        SpannableString sitesite1 = new SpannableString(site1);
        sitesite1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, site1.length(), 0);
        builderSite.append(sitesite1);

        SpannableStringBuilder builder2 = new SpannableStringBuilder();


        SpannableString key0 = new SpannableString(cothe);
        key0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, cothe.length(), 0);
        builder2.append(key0);

        SpannableString key = new SpannableString(note2 + note3 + note4);
        key.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        key.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder2.append(key);


        SpannableString textKey = new SpannableString(note2 + note3 + note4);
        textKey.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        textKey.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder1.append(textKey);

        SpannableString tvnameJobx = new SpannableString(note2 + note3 + note4);
        tvnameJobx.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorYellow)), 0, note.length(), 0);
        tvnameJobx.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builderx.append(tvnameJobx);

        SpannableString textKey1 = new SpannableString(hienco);
        textKey1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco.length(), 0);
        builder1.append(textKey1);


        SpannableString textKey2 = new SpannableString(hienco1);
        textKey2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, hienco1.length(), 0);
        textKey2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, hienco1.length(), 0);
        builder1.append(textKey2);

        SpannableString textKey3 = new SpannableString(hienco2);
        textKey3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco2.length(), 0);
        builder1.append(textKey3);

        SpannableString textKey4 = new SpannableString(note2 + note3 + note4);
        textKey4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorYellow)), 0, note.length(), 0);
        textKey4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder1.append(textKey4);


        SpannableString text3 = new SpannableString(note5);
        text3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note5.length(), 0);
        builder.append(text3);

        SpannableString text4 = new SpannableString(note6);
        text4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note6.length(), 0);
        builder.append(text4);

        SpannableString text5 = new SpannableString(note7);
        text5.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note7.length(), 0);
        builder.append(text5);

        SpannableString text6 = new SpannableString(note2 + note3 + note4);
        text6.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        text6.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder.append(text6);

        SpannableString textfinal = new SpannableString(note8);
        textfinal.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note8.length(), 0);
//        textfinal.setSpan(new UnderlineSpan(),0, note8.length(), 0);
        builder.append(textfinal);

        tv4.setText(builder);
        idNamejob.setText(builder1);
        tvAdress.setText(builder2);
        tvnameJob.setText(builderx);
        tvnJ.setText(builderx);
        namename.setText(builderName);
        idSite.setText(builderSite);
}

    private void customTextButtom() {
        String note1 = "Các công ty tuyển dụng ";
        String note2 = edNameJob.getText().toString().trim();
        String note4 = edCityx.getText().toString().trim();
        String note3 = " ở ";
        String hienco0 = " Việc làm ";
        String hienco = " hiện có ";
        String hienco1 = String.valueOf(Integer.parseInt(dataExp1)+ Integer.parseInt(dataExp2) + Integer.parseInt(dataExp3) + Integer.parseInt(dataExp4) + Integer.parseInt(dataExp5));;
        String site = "Dữ liệu được tổng hợp tại ";
        String site0 = "Hơn 100 site ";
        String site1 = "tìm việc uy tín trên cả nước, tính chính xác yêu cầu của bạn.";
        String cothe = "Bạn có thể làm công việc ";
        String hienco2 = " công ty đang tuyển dụng thuộc danh mục ";
        String note5 = " với mức lương trung bình từ ";
        String note6 = " 1- 3tr ";
        String note = note2 + note3 + note4;
        String note7 = "tùy vào từng vị trí và yêu cầu nhà tuyển dụng đưa ra. Bạn nên chọn công việc ";
        String note8 = ". Tại những công ty có mức lương cao tương ứng với năng lực trình độ của bạn để đảm bảo quyền lợi và công sức bạn bỏ ra. ";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString text1 = new SpannableString(note1);
        text1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note1.length() - 1, 0);

        builder.append(text1);

        SpannableString text2 = new SpannableString(note);
        text2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        text2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder.append(text2);


        SpannableStringBuilder builder1 = new SpannableStringBuilder();
        SpannableStringBuilder builderx = new SpannableStringBuilder();
        SpannableStringBuilder builderSite = new SpannableStringBuilder();
        SpannableStringBuilder builderName = new SpannableStringBuilder();

        SpannableString namebuild = new SpannableString(note2);
        namebuild.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorYellow)), 0, note2.length(), 0);
        builderName.append(namebuild);

        SpannableString textKey0 = new SpannableString(hienco0);
        textKey0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco0.length(), 0);
        builder1.append(textKey0);

        SpannableString sitesite = new SpannableString(site);
        sitesite.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, site.length(), 0);
        builderSite.append(sitesite);

        SpannableString sitesite0 = new SpannableString(site0);
        sitesite0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, site0.length(), 0);
        sitesite0.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, site0.length(), 0);
        builderSite.append(sitesite0);

        SpannableString sitesite1 = new SpannableString(site1);
        sitesite1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, site1.length(), 0);
        builderSite.append(sitesite1);
        SpannableStringBuilder builder2 = new SpannableStringBuilder();


        SpannableString key0 = new SpannableString(cothe);
        key0.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, cothe.length(), 0);
        builder2.append(key0);

        SpannableString key = new SpannableString(note2 + note3 + note4);
        key.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        key.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder2.append(key);


        SpannableString textKey = new SpannableString(note2 + note3 + note4);
        textKey.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        textKey.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder1.append(textKey);


        SpannableString tvnameJobx = new SpannableString(note2 + note3 + note4);
        tvnameJobx.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorYellow)), 0, note.length(), 0);
        tvnameJobx.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builderx.append(tvnameJobx);


        SpannableString textKey1 = new SpannableString(hienco);
        textKey1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco.length(), 0);
        builder1.append(textKey1);


        SpannableString textKey2 = new SpannableString(hienco1);
        textKey2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, hienco1.length(), 0);
        textKey2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, hienco1.length(), 0);
        builder1.append(textKey2);

        SpannableString textKey3 = new SpannableString(hienco2);
        textKey3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, hienco2.length(), 0);
        builder1.append(textKey3);

        SpannableString textKey4 = new SpannableString(note2 + note3 + note4);
        textKey4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorYellow)), 0, note.length(), 0);
        textKey4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder1.append(textKey4);


        SpannableString text3 = new SpannableString(note5);
        text3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note5.length(), 0);
        builder.append(text3);

        SpannableString text4 = new SpannableString(note6);
        text4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note6.length(), 0);
        builder.append(text4);

        SpannableString text5 = new SpannableString(note7);
        text5.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note7.length(), 0);
        builder.append(text5);

        SpannableString text6 = new SpannableString(note2 + note3 + note4);
        text6.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark)), 0, note.length(), 0);
        text6.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, note.length(), 0);
        builder.append(text6);

        SpannableString textfinal = new SpannableString(note8);
        textfinal.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textdetail)), 0, note8.length(), 0);
//        textfinal.setSpan(new UnderlineSpan(),0, note8.length(), 0);
        builder.append(textfinal);

        tv4.setText(builder);
        idNamejob.setText(builder1);
        tvAdress.setText(builder2);
        tvnameJob.setText(builderx);
        tvnJ.setText(builderx);
        namename.setText(builderName);
        idSite.setText(builderSite);
    }

    private void RetrofitGetDataButtom() {

        key = edNameJob.getText().toString().trim();
        province = cityBeansList.get(postionSpinner).getIdCity();
        Map<String, String> map = new HashMap<>();

        map.put("findkey", key);
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
                        customTextButtom();

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

        key = edNameJob.getText().toString().trim();
        province = cityBeansList.get(postionSpinner).getIdCity();
        Map<String, String> mapp = new HashMap<>();

        mapp.put("findkey", key);
        mapp.put("province", String.valueOf(province));


        JobRetrofit.getInstance().getDataCompany(mapp).enqueue(new Callback<List<DataCompany>>() {
            @Override
            public void onResponse(Call<List<DataCompany>> call, Response<List<DataCompany>> response) {
                if (response.code() == 200 && response.body() != null) {

                    adapterRCV.clearList();
                    adapterRCV.updateData(response.body());
//                    adapterRCV.clearList();
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
                        customText();

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
                } else {
                    Toast.makeText(DetailSalaryComparison.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
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

        cityBeansList.add(city1);
        cityBeansList.addAll(getCity("adress.json"));
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(DetailSalaryComparison.this, R.layout.spinner_layout, R.id.tvSpiner, cityBeansList);
        edCityx.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(DetailSalaryComparison.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }


    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        HistoryHomeAdapter historyAdapter = (HistoryHomeAdapter) listView.getAdapter();
        if (historyAdapter != null) {

            int numberOfItems = historyAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = historyAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    public void tvedit(View view) {
        Intent intent = new Intent(DetailSalaryComparison.this, HistoryActivity.class);
        startActivity(intent);
    }
}


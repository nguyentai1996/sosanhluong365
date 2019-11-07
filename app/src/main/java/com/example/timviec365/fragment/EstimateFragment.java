package com.example.timviec365.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.timviec365.R;
import com.example.timviec365.fragmentDialog.LoadSearchSalaryDialog;
import com.example.timviec365.model.Career;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.timviec365.activity.DetailSalaryComparison.hideSoftKeyboard;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateFragment extends Fragment {
    private static final String TAG = "EstimateFragment";

    private ArrayList<Career> careerArrayList = new ArrayList<>();
    private ImageView imgMore;
    private ImageView imgConectApp;
    private int postionSpinner = -1;

    private Button edFindSalary;
    private TextView nganhnghe;
    private AutoCompleteTextView edNameJob,edCareer, edLevel, edExperience, edform, edGender, edRank;


    private String postionCareer= "", nameCareer = "";
//    private boolean tvSelected = true;

    public EstimateFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estimate, container, false);
        setupUI(view.findViewById(R.id.parent));

        edFindSalary = view.findViewById(R.id.edFindSalary);
        imgMore = view.findViewById(R.id.imgMore);
        edLevel = view.findViewById(R.id.edLevel);
        imgConectApp = view.findViewById(R.id.imgConectApp);
        edNameJob = view.findViewById(R.id.edNameJobSearchSalary);
        edCareer = view.findViewById(R.id.SpCareerSearchSalary);
//        tvSelected = false;

        spinerNameJob();
        spinerCareer();

        imgConectApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("vn.timviec365.myapplication");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        edFindSalary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String key = edNameJob.getText().toString();

                for (int i = 0; i < careerArrayList.size(); i++) {

                Career career = careerArrayList.get(i);
                if (career.getNameCat().toLowerCase().equalsIgnoreCase(edCareer.getText().toString().trim().toLowerCase())) {
                    postionSpinner = i;
                }
            }
                if (key.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                } else if (postionSpinner == -1) {
                    Toast.makeText(getContext(), "Vui lòng chọn lại ngành nghề", Toast.LENGTH_SHORT).show();
                }

                else{
                    Intent intent = new Intent(getContext(), LoadSearchSalaryDialog.class);
                    intent.putExtra("key", key);
                    intent.putExtra("career", careerArrayList.get(postionSpinner).getIdCat());
                    intent.putExtra("nameCat", careerArrayList.get(postionSpinner).getNameCat());

                    Log.d("abc", String.valueOf(postionSpinner));
                    Log.d("abc", careerArrayList.get(postionSpinner).getNameCat());
                    startActivity(intent);
                }
            }
        });

        return view;
    }


    public List<JSONArray> getDataMoney(String filename) {
        JSONObject jsonArray = null;

        ArrayList<JSONArray> cList = new ArrayList<>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            java.lang.String json = new java.lang.String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cList.add(jsonArray.getJSONArray("db_category"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cList;
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



        Career career = new Career();
        career.setNameCat("Không yêu cầu");
        career.setIdCat("0");
        careerArrayList.add(career);
        careerArrayList.addAll(getDataCeareer("adress.json"));
        ArrayAdapter<Career> adapter = new ArrayAdapter<Career>(getActivity(), R.layout.spinner_layout,R.id.tvSpiner, careerArrayList);

        edCareer.setAdapter(adapter);
    }

    private void spinerNameJob() {
        ArrayList<String> itemArrayList = getNameJob("adress.json");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, R.id.tvSpiner, itemArrayList);
        edNameJob.setThreshold(0);
        edNameJob.setAdapter(adapter);
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
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

}


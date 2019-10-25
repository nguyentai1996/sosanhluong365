package com.example.timviec365.fragmentDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailDialogSearchSalary;
import com.example.timviec365.model.BangCap;
import com.example.timviec365.model.City;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMoreDialog extends DialogFragment {
    private static final String TAG = "FindMoreDialog";
    private Button edFindSalaryDiaLog;
    private ArrayList<City> cityBeansList = new ArrayList<>();
    private ArrayList<BangCap> bangCapArrayList = new ArrayList<>();
    private String title = "";
    private String career = "";

    public static FindMoreDialog newInstance(String key, String career) {
        FindMoreDialog frag = new FindMoreDialog();
        Bundle args = new Bundle();
        args.putString("findkey", key);
        args.putString("nganhnghe", career);
        frag.setArguments(args);
        return frag;
    }


    String[] capbacArray, levelArray, formArray, genderArray, expArray;
    List<String> capbac, level, form, gender, exp;
    private EditText tvExit;
    private Dialog dialog;
    private TextView tvForm, tvLevel, tvRank, tvCity, getTvForm, tvGender, tvExp;
    private int positionExpx, positionGenderx, positionRankx, positionLevelx, positionFormx;
    private String postionCityx, positionHinhthuc, postionBangcap, nameCity, nameBangcap;
    private String positionExp, positionGender, positionRank, positionLevel, positionForm;
    private Spinner spCity, spLevel, spExperience, spform, spGender, spRank;


    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_dialog_find_more, container, false);


        edFindSalaryDiaLog = view.findViewById(R.id.edFind);
        spCity = view.findViewById(R.id.spCity);
        spExperience = view.findViewById(R.id.spExperience);
        spGender = view.findViewById(R.id.spGender);
        spform = view.findViewById(R.id.spform);
        spRank = view.findViewById(R.id.spRank);
        spLevel = view.findViewById(R.id.spLevel);
        tvForm = view.findViewById(R.id.tvForm);
        tvExp = view.findViewById(R.id.tvExp);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvRank = view.findViewById(R.id.tvRank);
        tvCity = view.findViewById(R.id.tvCity);
        tvGender = view.findViewById(R.id.tvGender);

        title = getArguments().getString("findkey");
        career = getArguments().getString("nganhnghe");

        spinerAdress();
        spinerLevel();
        spinerExp();
        spinerGender();
        spinerForm();
        spinerRank();


        tvForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spform.setVisibility(View.VISIBLE);
                tvForm.setVisibility(View.GONE);
            }
        });


        tvLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spLevel.setVisibility(View.VISIBLE);
                tvLevel.setVisibility(View.GONE);
            }
        });


        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spCity.setVisibility(View.VISIBLE);
                tvCity.setVisibility(View.GONE);
            }
        });


        tvExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spExperience.setVisibility(View.VISIBLE);
                tvExp.setVisibility(View.GONE);
            }
        });


        tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spGender.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.GONE);
            }
        });


        tvRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spRank.setVisibility(View.VISIBLE);
                tvRank.setVisibility(View.GONE);
            }
        });
        edFindSalaryDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), DetailDialogSearchSalary.class);
                intent.putExtra("positionExpx", String.valueOf(positionExpx));
                intent.putExtra("positionCityx", String.valueOf(postionCityx));
                intent.putExtra("positionLevelx", String.valueOf(positionLevelx));
                intent.putExtra("positionFormx", String.valueOf(positionFormx));
                intent.putExtra("positionGenderx", String.valueOf(positionGenderx));
                intent.putExtra("positionRankx", String.valueOf(positionRankx));
                intent.putExtra("findkey", String.valueOf(title));
                intent.putExtra("career", String.valueOf(career));


                intent.putExtra("positionExp", positionExp);
                intent.putExtra("positionCity", nameCity);
                intent.putExtra("positionLevel", positionLevel);
                intent.putExtra("positionForm", positionForm);
                intent.putExtra("positionGender", positionGender);
                intent.putExtra("positionRank", positionRank);
                intent.putExtra("career", career);


                Log.d("a", (postionCityx) + "city");
                Log.d("a", (positionLevelx) + "level");
                Log.d("a", (positionFormx) + "HINH THUC");
                Log.d("a", (positionExpx) + "KINH NGHIEM");
                Log.d("a", (positionGenderx) + "GIOI TINH");
                Log.d("a", (positionRankx) + "CAP BAC");
                Log.d("a", (title) + "Caaaa");
                startActivity(intent);

            }
        });


        return view;
    }

    private void spinerGender() {
        genderArray = getResources().getStringArray(R.array.array_gioitinh);
        gender = new ArrayList<String>();
        gender = new ArrayList<String>(Arrays.asList(genderArray));

        /* selected item will look like a spinner set from XML */
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gender);

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionGender = spinnerArrayAdapter.getItem(i);
                if (positionGender.equals("Nam")) {
                    positionGenderx = 1;

                }
                if (positionGender.equals("Nữ")) {
                    positionGenderx = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGender.setAdapter(spinnerArrayAdapter);
    }


    private void spinerExp() {
        expArray = getResources().getStringArray(R.array.array_exp);
        exp = new ArrayList<String>();
        exp = new ArrayList<String>(Arrays.asList(expArray));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, exp);
        spExperience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionExp = spinnerArrayAdapter.getItem(i);


                if (positionExp.equals("0 - 1 năm kinh nghiệm")) {
                    positionExpx = 1;
                }

                if (positionExp.equals("1 - 2 năm kinh nghiệm")) {
                    positionExpx = 2;
                }

                if (positionExp.equals("2 - 5 năm kinh nghiệm")) {
                    positionExpx = 3;
                }
                if (positionExp.equals("5 - 10 năm kinh nghiệm")) {
                    positionExpx = 4;
                }
                if (positionExp.equals("Hơn 10 năm kinh nghiệm")) {
                    positionExpx = 5;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        spExperience.setAdapter(spinnerArrayAdapter);


    }

    private void spinerRank() {
        capbacArray = getResources().getStringArray(R.array.array_capbac);
        capbac = new ArrayList<String>();
        capbac = new ArrayList<String>(Arrays.asList(capbacArray));

        /* selected item will look like a spinner set from XML */
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, capbac);

        spRank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionRank = spinnerArrayAdapter.getItem(i);

                if (positionRank.equals("Không yêu cầu")) {
                    positionRankx = 0;
                }
                if (positionRank.equals("Mới tốt nghiệp")) {
                    positionRankx = 1;
                }
                if (positionRank.equals("Thực tập sinh")) {
                    positionRankx = 2;
                }
                if (positionRank.equals("Nhân viên")) {
                    positionRankx = 3;
                }
                if (positionRank.equals("Trưởng phòng")) {
                    positionRankx = 4;
                }
                if (positionRank.equals("Phó giám đốc")) {
                    positionRankx = 5;
                }
                if (positionRank.equals("Giám đốc")) {
                    positionRankx = 6;
                }
                if (positionRank.equals("Tổng giám đốc điều hành")) {
                    positionRankx = 7;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRank.setAdapter(spinnerArrayAdapter);

    }

    private void spinerForm() {
        formArray = getResources().getStringArray(R.array.array_hinhthuc);
        form = new ArrayList<String>();
        form = new ArrayList<String>(Arrays.asList(formArray));




        /* selected item will look like a spinner set from XML */
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, form) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                return super.getView(position, convertView, parent);
            }
        };

        spform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionForm = spinnerArrayAdapter.getItem(i);
                if (positionForm.equals("Không chọn")) {
                    positionFormx = 0;
                }
                if (positionForm.equals("Toàn thời gian cố định")) {
                    positionFormx = 1;
                }
                if (positionForm.equals("Toàn thời gian tạm thời")) {
                    positionFormx = 2;
                }
                if (positionForm.equals("Bán thời gian cố đinh")) {
                    positionFormx = 3;
                }
                if (positionForm.equals("Bán thời gian tạm thời")) {
                    positionFormx = 4;
                }
                if (positionForm.equals("Hợp đồng")) {
                    positionFormx = 5;
                }
                if (positionForm.equals("Khác")) {
                    positionFormx = 6;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spform.setAdapter(spinnerArrayAdapter);


    }

    private void spinerLevel() {
        levelArray = getResources().getStringArray(R.array.array_bangcap);
        level = new ArrayList<String>();
        level = new ArrayList<String>(Arrays.asList(levelArray));

        /* selected item will look like a spinner set from XML */
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, level);

        spLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionLevel = spinnerArrayAdapter.getItem(i);

                if (positionLevel.equals("Không chọn")) {
                    positionLevelx = 0;
                }
                if (positionLevel.equals("Tất cả trình độ")) {
                    positionLevelx = 1;

                }
                if (positionLevel.equals("Sau đại học")) {
                    positionLevelx = 2;
                }
                if (positionLevel.equals("Đại học")) {
                    positionLevelx = 3;
                }

                if (positionLevel.equals("Cao đẳng")) {
                    positionLevelx = 4;
                }

                if (positionLevel.equals("Trung cấp")) {
                    positionLevelx = 5;
                }

                if (positionLevel.equals("Trung học")) {
                    positionLevelx = 6;
                }

                if (positionLevel.equals("Khác")) {
                    positionLevelx = 7;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevel.setAdapter(spinnerArrayAdapter);
    }

    private void spinerAdress() {
        City city = new City();
        city.setIdCity("0");
        city.setNameCity("Toàn Quốc");

        cityBeansList.add(city);
        cityBeansList.addAll(getCity("adress.json"));

        ArrayAdapter<City> adapter = new ArrayAdapter<City>(getContext(), android.R.layout.simple_spinner_item, cityBeansList);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                postionCityx = cityBeansList.get(i).getIdCity();
                nameCity = cityBeansList.get(i).getNameCity();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spCity.setAdapter(adapter);
        spCity.setPrompt("aaaaaaaaaaa");
    }


    public ArrayList<String> getExp(String filename) {
        JSONObject jsonArray = null;

        ArrayList<String> cList = new ArrayList<>();
        try {
            InputStream inputStream = getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            java.lang.String json = new java.lang.String(data, "UTF-8");
            jsonArray = new JSONObject(json);
            if (jsonArray != null) {
                for (int i = 1; i < jsonArray.length(); i++) {
                    cList.add(String.valueOf(jsonArray.getJSONArray("array_kinh_nghiem").get(i)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cList;
    }


//    public ArrayList<String> getGender(String filename) {
//        {
//            JSONObject jsonArray = null;
//
//            ArrayList<String> cListgender = new ArrayList<>();
//            try {
//                InputStream inputStream = getResources().getAssets().open(filename);
//                int size = inputStream.available();
//                byte[] data = new byte[size];
//                inputStream.read(data);
//                inputStream.close();
//                java.lang.String json = new java.lang.String(data, "UTF-8");
//                jsonArray = new JSONObject(json);
//                if (jsonArray != null) {
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        cListgender.add(String.valueOf(jsonArray.getJSONArray("array_gioi_tinh").get(i)));
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return cListgender;
//        }
//    }


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

}

package com.example.timviec365.fragment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailSalaryComparison;
import com.example.timviec365.activity.HistoryActivity;
import com.example.timviec365.adapter.HistoryHomeAdapter;
import com.example.timviec365.fragmentDialog.LoadHomeDialog;
import com.example.timviec365.model.City;
import com.example.timviec365.model.History;
import com.example.timviec365.splDAO.HistoryDAO;
import com.example.timviec365.util.NetworkChangeReceiver;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.timviec365.activity.DetailSalaryComparison.hideSoftKeyboard;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnChartValueSelectedListener {
    private BroadcastReceiver NetworkChangeReceiver = null;
    private NetworkChangeReceiver etworkChangeReceiver ;
    private static int MAX_LENGTH = 100;
    private AutoCompleteTextView edNameJob;
    private AutoCompleteTextView edCityx;
    private Button btnFind;
    private TextView tvCitysp;
    private ArrayList<City> cityBeansList = new ArrayList<>();
    private int postionSpinner = -1, n;
    private TextView tvHistory;
    private ListView lvHis;
    private Context context;
    HistoryHomeAdapter historyAdapter = null;
    HistoryDAO historyDAO;
    public static List<History> arrHis = new ArrayList<>();


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        setupUI(view.findViewById(R.id.parent));


        NetworkChangeReceiver = new NetworkChangeReceiver();

        edCityx = view.findViewById(R.id.edCityx);
        btnFind = view.findViewById(R.id.btnFind);
        edNameJob = view.findViewById(R.id.edNameJob);
        lvHis = view.findViewById(R.id.lvHis);
        tvHistory = view.findViewById(R.id.tvHistory);
        historyDAO = new HistoryDAO(getContext());
        arrHis = historyDAO.getAllHistory();
        historyAdapter = new HistoryHomeAdapter(context, arrHis);
        lvHis.setAdapter(historyAdapter);
        Collections.reverse(arrHis);
        setListViewHeightBasedOnItems(lvHis);

        lvHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailSalaryComparison.class);
                Bundle b = new Bundle();


                b.putString("find", arrHis.get(position).getKey());
                b.putString("pro", arrHis.get(position).getIdCity());
                b.putString("namecity", arrHis.get(position).getNameCity());

                Log.d("nameidCityx", arrHis.get(position).getNameCity());
                Log.d("idCityx", arrHis.get(position).getIdCity());
                Log.d("idCityxMain", arrHis.get(position).getIdMain());
                Log.d("idCityxkey", arrHis.get(position).getKey());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvHis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });


        sipner();
        getAdress();

        tvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < cityBeansList.size(); i++) {

                    City city = cityBeansList.get(i);
                    if (city.getNameCity().toLowerCase().equalsIgnoreCase(edCityx.getText().toString().trim().toLowerCase())) {
                        postionSpinner = i;
                    }
                }
                String key = edNameJob.getText().toString().trim();
                if (key.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show();
                }
                if (postionSpinner == -1) {
                    Toast.makeText(getContext(), "Địa điểm bạn nhập vào chưa đúng", Toast.LENGTH_SHORT).show();
                } else {

                    History history = new History(random(), edNameJob.getText().toString().trim(), cityBeansList.get(postionSpinner).getIdCity(), edCityx.getText().toString().trim());
                    if (historyDAO.insertHistory(history) > 0) {
                        Intent intent = new Intent(getContext(), LoadHomeDialog.class);
                        intent.putExtra("find", key);
                        intent.putExtra("pro", cityBeansList.get(postionSpinner).getIdCity());
                        intent.putExtra("namecity", cityBeansList.get(postionSpinner).getNameCity());
                        Log.d("potion", String.valueOf(cityBeansList.get(postionSpinner).getIdCity()));
                        Log.d("potion", String.valueOf(Math.random()));
                        startActivity(intent);

                    } else {
                        Toast.makeText(getContext(), "Thêm dữ liệu vào lịch sử thất bại",
                                Toast.LENGTH_SHORT).show();
                    }


                }
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


        City city1 = new City();
        city1.setNameCity("Toàn Quốc");
        city1.setIdCity("0");

        cityBeansList.add(city1);
        cityBeansList.addAll(getCity("adress.json"));
        ArrayAdapter<City> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_layout, R.id.tvSpiner, cityBeansList);

        edCityx.setAdapter(adapter);


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


    public void broadcastIntent() {
        context.registerReceiver(NetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

}
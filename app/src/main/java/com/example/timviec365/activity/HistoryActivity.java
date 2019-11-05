package com.example.timviec365.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.timviec365.R;
import com.example.timviec365.adapter.HistoryAdapter;
import com.example.timviec365.model.History;
import com.example.timviec365.splDAO.HistoryDAO;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    ListView lvHis;
    private SearchView searchView;
    HistoryAdapter historyAdapter = null;
//    HistoryDAO historyDAO;
    HistoryDAO historyDAO;
    public static List<History> arrHis = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        historyDAO = new HistoryDAO(this);
        historyAdapter = new HistoryAdapter(this, arrHis);
        arrHis = historyDAO.getAllHistory();
        lvHis.setAdapter(historyAdapter);
        registerForContextMenu(lvHis);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });

        lvHis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoryActivity.this,DetailSalaryComparison.class);
                Bundle b = new Bundle();

                b.putString("find", arrHis.get(position).getKey());
                b.putString("pro", arrHis.get(position).getIdCity());
                b.putString("namecity", arrHis.get(position).getNameCity());

                Log.d("ads", arrHis.get(position).getIdMain());

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


    }

    @Override
    protected void onResume() {
        super.onResume();
        arrHis.clear();
        arrHis = historyDAO.getAllHistory();
        historyAdapter.changeDataset(historyDAO.getAllHistory());
    }

    private void initView() {
        setContentView(R.layout.activity_history);

        lvHis = (ListView) findViewById(R.id.lvHistory);
        searchView = findViewById(R.id.searchView);

    }

    private void searchContact(String keyword) {
        HistoryDAO historyDAO = new HistoryDAO(getApplicationContext());
        List<History> contacts = historyDAO.search(keyword);
        if (contacts != null) {
            lvHis.setAdapter(new HistoryAdapter(getApplicationContext(), contacts));
        }
    }




}



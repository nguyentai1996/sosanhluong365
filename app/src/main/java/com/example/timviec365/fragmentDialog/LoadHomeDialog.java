package com.example.timviec365.fragmentDialog;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailSalaryComparison;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class LoadHomeDialog extends AppCompatActivity {
    private ProgressBar progressBar;
    private BroadcastReceiver NetworkChangeReceiver = null;
    private String key, thanhpho, namecity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_load_dialog);

            ProgressBar progressBar = findViewById(R.id.spin_kit);
            Sprite doubleBounce = new Circle();
            progressBar.setIndeterminateDrawable(doubleBounce);

            key = getIntent().getStringExtra("find");
            thanhpho = getIntent().getStringExtra("pro");
            namecity = getIntent().getStringExtra("namecity");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    dowork();
                    star();
                    finish();
                }

            }).start();

        }


    private void star() {
        broadcastIntent();
        Intent intent = new Intent(LoadHomeDialog.this, DetailSalaryComparison.class);
        intent.putExtra("find",key);
        intent.putExtra("pro",thanhpho);
        intent.putExtra("namecity",namecity);

        startActivity(intent);
    }

    private void dowork() {
        for (int proges = 0; proges < 100; proges += 50) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(proges);
            } catch (Exception e) {
            }
        }

    }


    public void broadcastIntent() {
        registerReceiver(NetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }



}

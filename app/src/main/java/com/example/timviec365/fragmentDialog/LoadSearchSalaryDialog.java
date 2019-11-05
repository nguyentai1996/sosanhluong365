package com.example.timviec365.fragmentDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailSearchSalaryActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class LoadSearchSalaryDialog extends AppCompatActivity {
    private ProgressBar progressBar;
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

        key = getIntent().getStringExtra("key");
        thanhpho = getIntent().getStringExtra("career");
        namecity = getIntent().getStringExtra("nameCat");




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
        Intent intent = new Intent(LoadSearchSalaryDialog.this, DetailSearchSalaryActivity.class);
        intent.putExtra("key",key);
        intent.putExtra("career",thanhpho);
        intent.putExtra("nameCat",namecity);

        startActivity(intent);
    }

    private void dowork() {
        for (int proges = 0; proges < 100; proges += 30) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(proges);
            } catch (Exception e) {
            }
        }

    }

}

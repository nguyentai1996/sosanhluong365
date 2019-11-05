package com.example.timviec365.fragmentDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timviec365.R;
import com.example.timviec365.activity.DetailDialogSearchSalary;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class LoadFindDialog extends AppCompatActivity {
    private ProgressBar progressBar;
    private String namecity, positionExp, positionLevel, positionForm, positionGender, positionRank, positionCity, positionExpx, positionCityx, positionLevelx, positionFormx, positionGenderx, positionRankx, findkey, career;

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


        positionCityx = getIntent().getStringExtra("positionCityx");
        findkey = getIntent().getStringExtra("findkey");
        career = getIntent().getStringExtra("career");
        namecity = getIntent().getStringExtra("nameCat");


        positionExp = getIntent().getStringExtra("positionExp");
        positionCity = getIntent().getStringExtra("positionCity");
        positionLevel = getIntent().getStringExtra("positionLevel");
        positionForm = getIntent().getStringExtra("positionForm");
        positionGender = getIntent().getStringExtra("positionGender");
        positionRank = getIntent().getStringExtra("positionRank");
        career = getIntent().getStringExtra("career");


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
        Intent intent = new Intent(LoadFindDialog.this, DetailDialogSearchSalary.class);

        intent.putExtra("positionCityx", positionCityx);
        intent.putExtra("findkey", findkey);
        intent.putExtra("career", namecity);

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

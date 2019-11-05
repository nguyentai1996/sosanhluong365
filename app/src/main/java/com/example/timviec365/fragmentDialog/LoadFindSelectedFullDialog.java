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

public class LoadFindSelectedFullDialog extends AppCompatActivity {
    private ProgressBar progressBar;
    private String positionExp,nameCat,positionLevel,positionForm,positionGender,positionRank,positionCity,positionExpx, positionCityx, positionLevelx,positionFormx,positionGenderx,positionRankx,findkey,career;

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

        positionExpx = getIntent().getStringExtra("positionExpx");
        positionCityx = getIntent().getStringExtra("positionCityx");
        positionLevelx = getIntent().getStringExtra("positionLevelx");
        positionFormx = getIntent().getStringExtra("positionFormx");
        positionGenderx = getIntent().getStringExtra("positionGenderx");
        positionRankx = getIntent().getStringExtra("positionRankx");
        findkey = getIntent().getStringExtra("findkey");
        career = getIntent().getStringExtra("career");
        nameCat = getIntent().getStringExtra("nameCat");


        positionExp = getIntent().getStringExtra("positionExp");
        positionCity = getIntent().getStringExtra("positionCity");
        positionLevel = getIntent().getStringExtra("positionLevel");
        positionForm = getIntent().getStringExtra("positionForm");
        positionGender = getIntent().getStringExtra("positionGender");
        positionRank = getIntent().getStringExtra("positionRank");







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
        Intent intent = new Intent(LoadFindSelectedFullDialog.this, DetailDialogSearchSalary.class);
        intent.putExtra("positionExpx",positionExpx);
        intent.putExtra("positionCityx",positionCityx);
        intent.putExtra("positionLevelx",positionLevelx);
        intent.putExtra("positionFormx",positionFormx);
        intent.putExtra("positionGenderx",positionGenderx);
        intent.putExtra("positionRankx",positionRankx);
        intent.putExtra("positionExp",positionExp);
        intent.putExtra("positionCity",positionCity);
        intent.putExtra("positionLevel",positionLevel);
        intent.putExtra("positionForm",positionForm);
        intent.putExtra("positionGender",positionGender);
        intent.putExtra("positionRank",positionRank);
        intent.putExtra("findkey",findkey);
        intent.putExtra("career",career);
        intent.putExtra("nameCat",nameCat);

        https://timviec365.vn/gioi-thieu-chung.html

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

package com.example.timviec365.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.timviec365.R;
import com.example.timviec365.fragment.AboutFragment;
import com.example.timviec365.fragment.GrossNetFragment;
import com.example.timviec365.fragment.HomeFragment;
import com.example.timviec365.fragment.compareFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private BottomNavigationView navigationView;
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;
    private GrossNetFragment grossNetFragment;
    private compareFragment compareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.mainBottomNav);

        LoadFragment(new HomeFragment());


        navigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.bottom_action_home:
                fragment = new HomeFragment();
                break;

            case R.id.bottom_action_note:
                fragment = new AboutFragment();
                break;

            case R.id.bottom_action_notifi:
                fragment = new GrossNetFragment();
                break;

            case R.id.bottom_action_setting:
                fragment = new compareFragment();
                break;
        }
        return LoadFragment(fragment);
    }
}



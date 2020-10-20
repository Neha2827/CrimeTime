package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.project.crimetime.Adapters.TabAdapter;
import com.project.crimetime.Fragments.AdminLogin;
import com.project.crimetime.Fragments.UserLogin;

public class LogIn extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_new);
        Bundle data=getIntent().getExtras();
        tabLayout = findViewById(R.id.tablyout);
        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        if(data!=null) {
            boolean IS_LOGOUT = data.getBoolean("LOGOUT");
            boolean IS_USER = data.getBoolean("USER_LOGIN");
            if (IS_LOGOUT) {
                if (IS_USER) {
                    viewPager.setCurrentItem(0);

                } else {
                    viewPager.setCurrentItem(1);
                }
            }
        }
        if(data!=null){
            boolean IS_LOGOUT=data.getBoolean("LOGOUT");
            boolean IS_ADMIN=data.getBoolean("ADMIN_LOGIN");
            if(IS_LOGOUT){
                if(IS_ADMIN){
                    viewPager.setCurrentItem(0);
                }
                else {
                    viewPager.setCurrentItem(1);
                }
            }
        }

    }

    void setupViewPager(ViewPager viewPager){

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new UserLogin(), "User LogIn");
        tabAdapter.addFragment(new AdminLogin(), "Admin Login");
        viewPager.setAdapter(tabAdapter);
    }
}
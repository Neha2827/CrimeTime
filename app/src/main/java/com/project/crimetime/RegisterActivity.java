package com.project.crimetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.project.crimetime.Adapters.TabAdapter;
import com.project.crimetime.Fragments.AdminRegister;
import com.project.crimetime.Fragments.UserRegister;

public class RegisterActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tabLayout = findViewById(R.id.tablyout);
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    void setUpViewPager(ViewPager viewPager){

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserRegister(), "User Register");
        adapter.addFragment(new AdminRegister(), "Admin Register");
        viewPager.setAdapter(adapter);
    }
}
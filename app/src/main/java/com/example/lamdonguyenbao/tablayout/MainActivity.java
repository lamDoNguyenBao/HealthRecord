package com.example.lamdonguyenbao.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lamdonguyenbao.tablayout.adapter.ViewPagerAdapter;
import com.example.lamdonguyenbao.tablayout.fragment.HistoryFragment;
import com.example.lamdonguyenbao.tablayout.fragment.HospitalFragment;
import com.example.lamdonguyenbao.tablayout.fragment.InforFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private int[] tabIcons = {
            R.drawable.tab_person_black_24dp,
            R.drawable.tab_history_black_24dp,
            R.drawable.tab_person_black_24dp
    };

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InforFragment(), "");
        adapter.addFragment(new HistoryFragment(), "");
        adapter.addFragment(new HospitalFragment(), "");
        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}

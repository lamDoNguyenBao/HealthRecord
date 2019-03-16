package com.example.lamdonguyenbao.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.onesignal.OneSignal;
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
        setUpOneSignal();
    }

    private int[] tabIcons = {
            R.drawable.ic_filter_1_white_24dp,
            R.drawable.ic_filter_2_white_24dp,
            R.drawable.ic_filter_3_white_24dp
    };
    private void setUpOneSignal(){
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InforFragment(), "Information");
        adapter.addFragment(new HistoryFragment(), "History");
        adapter.addFragment(new HospitalFragment(), "Hospital");
        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}

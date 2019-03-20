package com.example.lamdonguyenbao.tablayout;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lamdonguyenbao.tablayout.model.User;
import com.onesignal.OneSignal;
import com.example.lamdonguyenbao.tablayout.adapter.ViewPagerAdapter;
import com.example.lamdonguyenbao.tablayout.fragment.HistoryFragment;
import com.example.lamdonguyenbao.tablayout.fragment.HospitalFragment;
import com.example.lamdonguyenbao.tablayout.fragment.InforFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//
//    private static final Activity ActivityCompat = ;
    private static int REQUEST_PHONE_CALL = 1;
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



    @Override
    protected void onStart() {
        super.onStart();
        getCallPhonePermission();
    }

    private void getCallPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
    }

    private int[] tabIcons = {
            R.drawable.tab_person_black_24dp,
            R.drawable.tab_history_black_24dp,
            R.drawable.tab_person_black_24dp
    };
    private void setUpOneSignal(){
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
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

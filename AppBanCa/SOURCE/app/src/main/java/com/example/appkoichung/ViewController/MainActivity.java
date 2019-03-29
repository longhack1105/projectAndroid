package com.example.appkoichung.ViewController;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appkoichung.R;
import com.example.appkoichung.ViewController.Base.BaseActivity;
import com.example.appkoichung.ViewController.Base.BaseFragment;
import com.example.appkoichung.ViewController.Batch.BatchFragment;

public class MainActivity extends BaseActivity {

    private TextView txtTitle;

    ImageView imgLogout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BaseFragment fragment = new BatchFragment();
            switch (item.getItemId()) {
                case R.id.navigation_thong_ke:
                    getSupportActionBar().setTitle("Thông kê");
                    break;
                case R.id.navigation_hop_dong:
                    getSupportActionBar().setTitle("Hợp đồng");
                    break;
                case R.id.navigation_lo_hang:
                    getSupportActionBar().setTitle("Lô hàng");
                    fragment = new BatchFragment();
                    break;
                case R.id.navigation_don_hang:
                    getSupportActionBar().setTitle("Đơn hàng");
                    break;
                case R.id.navigation_dai_ly:
                    getSupportActionBar().setTitle("Đại lý");
                    break;
            }
            transaction.replace(R.id.parent, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.activity_main, (ViewGroup) findViewById(R.id.container));
        BottomNavigationView navigation = findViewById(R.id.bnv_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_thong_ke);
        setupView();
    }

    private void setupView() {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        return null;
    }

}

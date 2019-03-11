package com.example.koichung2.ViewController.Batch;

import android.support.v4.app.Fragment;

import com.example.koichung2.ViewController.Base.FragmentWithListView;
import com.example.koichung2.ViewController.Base.FragmentWithTab;

/**
 * A simple {@link Fragment} subclass.
 */

public class BatchFragment extends FragmentWithTab {
public static BatchFragment batchFragment = null;
    @Override
    protected void setAdapter() {
        pageAdapter.addFragment(ListBatchFragment.newInstance(FragmentWithListView.TAB_ALL_BATCH),"Tất cả");
        pageAdapter.addFragment(ListBatchFragment.newInstance(FragmentWithListView.TAB_HAVE_BILL),"Có HĐ");
        pageAdapter.addFragment(ListBatchFragment.newInstance(FragmentWithListView.TAB_NOT_BILL),"Chưa HĐ");
    }
    public static BatchFragment getInstance(){
        if (batchFragment==null){
            batchFragment=new BatchFragment();
        }
        return batchFragment;
    }
}

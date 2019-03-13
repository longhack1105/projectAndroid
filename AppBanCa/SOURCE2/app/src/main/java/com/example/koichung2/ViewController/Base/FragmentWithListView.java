package com.example.koichung2.ViewController.Base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.koichung2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithListView extends BaseFragment {

    public static int TAB_ALL_BATCH = 0;
    public static int TAB_HAVE_BILL = 1;
    public static int TAB_NOT_BILL = 2;
    public static String TYPE_KEY = "type";

    public int type;
    View rootView;

    protected ListView lvFragWithLv;
    protected SwipeRefreshLayout swRFragmentWithLv;

    public FragmentWithListView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(TYPE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fragment_with_list_view, container, false);
        init();
        return rootView;
    }

    public void init() {
        lvFragWithLv = rootView.findViewById(R.id.lv_frag_with_lv);
        swRFragmentWithLv = rootView.findViewById(R.id.sw_r_fragment_with_lv);
        swRFragmentWithLv.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });
        setAdapter();
    }

    public void getData() {
        swRFragmentWithLv.setRefreshing(true);
    }

    public abstract void setAdapter();

}

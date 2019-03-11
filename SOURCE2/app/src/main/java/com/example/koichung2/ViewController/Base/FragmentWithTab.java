package com.example.koichung2.ViewController.Base;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koichung2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithTab extends BaseFragment {

    TabLayout tab;
    ViewPager vp;
    FloatingActionButton fab;

    View rootView;
    protected ViewPageAdapter pageAdapter;

    public FragmentWithTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fragment_with_tab, container, false);
        init();
        return rootView;
    }

    public void init() {
        tab = rootView.findViewById(R.id.tab);
        vp = rootView.findViewById(R.id.vp);
        fab = rootView.findViewById(R.id.fab);
        pageAdapter = new ViewPageAdapter(getChildFragmentManager());
        setAdapter();
        vp.setAdapter(pageAdapter);
        tab.setupWithViewPager(vp);
    }

    protected abstract void setAdapter();
}

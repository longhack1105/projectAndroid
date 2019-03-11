package com.example.koichung2.ViewController.Batch;

import android.os.Bundle;
import android.widget.Toast;

import com.example.koichung2.Model.Batch.Batch;
import com.example.koichung2.Model.Batch.Result;
import com.example.koichung2.Networks.APIServer;
import com.example.koichung2.Networks.RetrofitClient;
import com.example.koichung2.Until.AppConfig;
import com.example.koichung2.Until.Until;
import com.example.koichung2.ViewController.Base.FragmentWithListView;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBatchFragment extends FragmentWithListView {
    public static ListBatchFragment listBatchFragment = null;
    ArrayList<Result> batchArrayList = new ArrayList<>();
    BatchAdapter batchAdapter;

    public static ListBatchFragment newInstance(int type) {
        listBatchFragment = new ListBatchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        listBatchFragment.setArguments(bundle);
        return listBatchFragment;
    }

    @Override
    public void getData() {
        super.getData();
        Until.jsonBody();
        Until.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        if (type == FragmentWithListView.TAB_ALL_BATCH) {
            Until.jsonObject.addProperty("status", 0);
        } else if (type == FragmentWithListView.TAB_HAVE_BILL) {
            Until.jsonObject.addProperty("status", 1);
        } else {
            Until.jsonObject.addProperty("status", -1);
        }
        getDataBatch(Until.jsonObject);
    }

    public void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getListBatch(jsonObject).enqueue(new Callback<Batch>() {
            @Override
            public void onResponse(Call<Batch> call, Response<Batch> response) {
                swRFragmentWithLv.setRefreshing(false);
                if (response.body().getStatus() == 1) {
                    batchArrayList.clear();
                    batchArrayList.addAll(response.body().getResult());
                    batchAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Batch> call, Throwable t) {
                swRFragmentWithLv.setRefreshing(false);
            }
        });
    }

    @Override
    public void setAdapter() {
        batchAdapter = new BatchAdapter(batchArrayList,getActivity());
        lvFragWithLv.setAdapter(batchAdapter);
    }
}

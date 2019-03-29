package com.example.appkoichung.ViewController.Batch;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appkoichung.Model.Batch.Batch;
import com.example.appkoichung.Model.Batch.BatchRespone;
import com.example.appkoichung.Network.APIServer;
import com.example.appkoichung.Network.RetrofitClient;
import com.example.appkoichung.R;
import com.example.appkoichung.Util.AppConfig;
import com.example.appkoichung.Util.Util;
import com.example.appkoichung.ViewController.Base.FragmentListView;
import com.example.appkoichung.ViewController.Batch.Adapter.BatchAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatchFragment extends FragmentListView {
    public static BatchFragment batchFragment = null;
    BatchAdapter adapter;
    ArrayList<Batch> arrayData = new ArrayList<>();

    public static BatchFragment getInstance() {
        if (batchFragment == null) {
            batchFragment = new BatchFragment();
        }
        return batchFragment;
    }

    @Override
    protected void configAdapter() {
        adapter = new BatchAdapter(arrayData, getActivity());
        lvFrag.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_batch, menu);

        Util.baseJsonBody();
        Util.jsonObject.addProperty(AppConfig.USER_ID, AppConfig.getUserID(getActivity()));
        Util.jsonObject.addProperty(AppConfig.STATUS, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Util.baseJsonBody();
        Util.jsonObject.addProperty(AppConfig.USER_ID, AppConfig.getUserID(getActivity()));
        switch (item.getItemId()) {
            case R.id.mnu_batch_all:
                Util.jsonObject.addProperty(AppConfig.STATUS, 0);
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_batch_have_activities:
                Util.jsonObject.addProperty(AppConfig.STATUS, 1);
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_batch_no_activities:
                Util.jsonObject.addProperty(AppConfig.STATUS, -1);
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_batch_add:
//                Intent intent=new Intent(getActivity(),AddBatchActivity.class);
//                getActivity().startActivity(intent);
                break;
        }
        item.setChecked(true);
        return true;
    }

    @Override
    protected void getData() {
        super.getData();
        getDataBatch(Util.jsonObject);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getClient().create(APIServer.class).getBatch(Util.jsonObject).enqueue(new Callback<BatchRespone>() {
            @Override
            public void onResponse(Call<BatchRespone> call, Response<BatchRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.body().getStatus() == 1){
                    arrayData.clear();
                    arrayData.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BatchRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Mời kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.app.softxperttask.Repositories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import com.app.softxperttask.Model.CarModel;
import com.app.softxperttask.Model.ParentResponse;
import com.app.softxperttask.Network.Api;
import com.app.softxperttask.Tools.LoadEnd;
import com.app.softxperttask.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository instance;

    private Repository() {

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();

        }
        return instance;
    }


    public void GetData(final MutableLiveData<ArrayList<CarModel>> m, final Context context, int page, final LoadEnd loadEnd) {
        try {
            Api.getClient().getCars(page).enqueue(new Callback<ParentResponse<List<CarModel>>>() {
                @Override
                public void onResponse(Call<ParentResponse<List<CarModel>>> call, Response<ParentResponse<List<CarModel>>> response) {
                    if (response.body() == null || response.body().getData() == null) {
                        //    Toast.makeText(context, "Error In Parsing Data", Toast.LENGTH_SHORT).show();
                        loadEnd.end();
                        return;
                    }
                    m.setValue((ArrayList<CarModel>) response.body().getData());
                }

                @Override
                public void onFailure(Call<ParentResponse<List<CarModel>>> call, Throwable t) {
                    loadEnd.end();
                    if (!Utils.isNetworkConnected(context)) {
                        Toast.makeText(context, "Network Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            loadEnd.end();
        }
    }
}

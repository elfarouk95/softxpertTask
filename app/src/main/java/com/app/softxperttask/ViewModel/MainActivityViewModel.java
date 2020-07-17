package com.app.softxperttask.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.app.softxperttask.Model.CarModel;
import com.app.softxperttask.Repositories.Repository;
import com.app.softxperttask.Tools.LoadEnd;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CarModel>> mutableLiveData;
    private Repository repository = Repository.getInstance();

    public LiveData<ArrayList<CarModel>> getPending() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void Get(Context c, int page, LoadEnd loadEnd) {
        repository.GetData(mutableLiveData, c, page,loadEnd);
    }
}

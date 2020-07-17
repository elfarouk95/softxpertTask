package com.app.softxperttask.Network;

import com.app.softxperttask.Model.CarModel;
import com.app.softxperttask.Model.ParentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/v1/cars")
    Call<ParentResponse<List<CarModel>>> getCars(@Query("page") int page);
}

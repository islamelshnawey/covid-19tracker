package com.is.covid_19_tracker.data.remote.api;

import androidx.lifecycle.LiveData;

import com.is.covid_19_tracker.data.local.entity.Corona;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 *
 * @author Islam Elshnawey
 * @date 4/9/20
 * <p>
 * is.elshnawey@gmail.com
 **/
public interface ApiService {

    @GET("countries")
    LiveData<ApiResponse<List<Corona>>> getCoronaList(@Query("sort") String sort);

    @GET("countries/egypt")
    LiveData<ApiResponse<Corona>> getCoronaCountry();


}

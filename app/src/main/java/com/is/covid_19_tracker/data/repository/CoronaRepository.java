package com.is.covid_19_tracker.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.gson.JsonElement;
import com.is.covid_19_tracker.AppExecutors;
import com.is.covid_19_tracker.data.AppDatabase;
import com.is.covid_19_tracker.data.NetworkBoundResource;
import com.is.covid_19_tracker.data.Resource;
import com.is.covid_19_tracker.data.local.dao.CoronaDao;
import com.is.covid_19_tracker.data.local.entity.Corona;
import com.is.covid_19_tracker.data.remote.api.ApiResponse;
import com.is.covid_19_tracker.data.remote.api.ApiService;
import com.is.covid_19_tracker.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author Islam Elshnawey
 * @date 4/10/20
 * <p>
 * is.elshnawey@gmail.com
 **/
public class CoronaRepository extends BaseRepository {

    // region Variables

    private CoronaDao coronaDao;
    private ApiService apiService;

    //endregion

    // region Constructor

    @Inject
    CoronaRepository(ApiService apiService, AppExecutors appExecutors, AppDatabase db, CoronaDao coronaDao) {
        super(apiService, appExecutors, db);
        this.apiService = apiService;
        this.coronaDao = coronaDao;
    }

    //endregion

    // region Methods

    /**
     * Get Corona list
     * <p>
     * We are using this method to fetch the movies list
     * NetworkBoundResource is part of the Android architecture
     * components. You will notice that this is a modified version of
     * that class. That class is based on LiveData but here we are
     * using Observable from RxJava.
     * <p>
     * There are three methods called:
     * a. fetch data from server
     * b. fetch data from local
     * c. save data from api in local
     * <p>
     * So basically we fetch data from server, store it locally
     * and then fetch data from local and update the UI with
     * this data.
     *
     * @return
     */
    public LiveData<Resource<List<Corona>>> getCoronaList() {

        return new NetworkBoundResource<List<Corona>, List<Corona>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Corona> jsonElement) {
                Utils.log("SaveCallResult of getCoronasList.");

                try {
                    db.runInTransaction(() -> {
                        coronaDao.deleteCoronasList();

                       /* List<Corona> coronaList = new ArrayList<>();

                        JSONArray jsonArray = new JSONArray(jsonElement);

                        for(int i=0; i<jsonArray.length(); i++){

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JSONObject imageObject = jsonObject.getJSONObject("countryInfo");

                            String imageUrl = imageObject.getString("flag");
                            String country   = jsonObject.getString("country");
                            String cases     = "Confirmed" + jsonObject.getInt("cases");
                            String deaths    = "Deaths"+ jsonObject.getInt("deaths");
                            String recovered = "Recoverd" + jsonObject.getInt("recovered");

                            coronaList.add(new Corona(imageUrl, country, cases, deaths, recovered));
                        }*/


                        coronaDao.insert(jsonElement);
                    });
                } catch (Exception ex) {
                    Utils.errorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Corona> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Corona>> loadFromDb() {
                Utils.log("Load Recent Coronas List From Db");
                return coronaDao.getCoronaList();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Corona>>> createCall() {
                return apiService.getCoronaList("cases");

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.log("Fetch Failed (getRecentCoronasList) : " + message);
            }
        }.asLiveData();
    }

    public int getCount() {
        return coronaDao.getCoronaListCount();
    }

    /**
     * Load Country Details
     */
    public Corona getCountryDetailsFromDb(String country) {
        return coronaDao.getCoronaByCountry(country);
    }

    //endregion
}

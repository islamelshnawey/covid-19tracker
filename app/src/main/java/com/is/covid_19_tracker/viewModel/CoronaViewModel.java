package com.is.covid_19_tracker.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.is.covid_19_tracker.data.Resource;
import com.is.covid_19_tracker.data.local.entity.Corona;
import com.is.covid_19_tracker.data.repository.CoronaRepository;
import com.is.covid_19_tracker.ui.base.BaseViewModel;
import com.is.covid_19_tracker.util.AbsentLiveData;
import com.is.covid_19_tracker.util.Utils;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Islam Elshnawey
 * @date 4/10/20
 * <p>
 * is.elshnawey@gmail.com
 **/
public class CoronaViewModel extends BaseViewModel {

    private CoronaRepository coronaRepository;

    private final LiveData<Resource<List<Corona>>> worldListData;
    private MutableLiveData<TmpDataHolder> worldListObj = new MutableLiveData<>();

    @Inject
    public CoronaViewModel(CoronaRepository repository) {

        this.coronaRepository = repository;

        worldListData = Transformations.switchMap(worldListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.log("World List.");
            return repository.getCoronaList();
        });

    }

    //region WorldList

    public void setWorldListObj() {

        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            // tmpDataHolder.limit = limit;
            // tmpDataHolder.offset = offset;
            // tmpDataHolder.userId = userId;
            // tmpDataHolder.deviceToken = deviceToken;
            worldListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Corona>>> getWorldListData() {
        return worldListData;
    }

    //endregion

    // region By Country

    public Corona getDataByCountry(String country) {
        return coronaRepository.getCountryDetailsFromDb(country);
    }

    //endregion

    /**
     * Temporary Data Holder Can contain params to be send to server
     */
    class TmpDataHolder {
        public String limit = "";
        public String offset = "";
        public Boolean isConnected = false;
        public String notificationId = "";
        public String userId = "";
        public String deviceToken = "";
    }
}

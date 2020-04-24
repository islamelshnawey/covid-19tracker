package com.is.covid_19_tracker.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.is.covid_19_tracker.Constants;
import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.data.Status;
import com.is.covid_19_tracker.data.binding.FragmentDataBindingComponent;
import com.is.covid_19_tracker.data.local.entity.Corona;
import com.is.covid_19_tracker.databinding.FragmentDetailsBinding;
import com.is.covid_19_tracker.ui.base.BaseFragment;
import com.is.covid_19_tracker.ui.base.DataBoundListAdapter;
import com.is.covid_19_tracker.util.AutoClearedValue;
import com.is.covid_19_tracker.util.Utils;
import com.is.covid_19_tracker.viewModel.CoronaViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BaseFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private CoronaViewModel coronaViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentDetailsBinding> binding;

    private  String country;

    //endregion

    //region Constructor

    public DetailsFragment() {
        // Required empty public constructor
    }

    //endregion

    //region Overrides
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailsBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onDispatched() {
    }

    @Override
    protected void initUIAndActions() {

    }

    @Override
    protected void initViewModels() {
        coronaViewModel = new ViewModelProvider(this, viewModelFactory).get(CoronaViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent().getExtras() != null) {
                    String COUNTRY = Constants.COUNTRY;
                    country = getActivity().getIntent().getExtras().getString(COUNTRY);
                }
            }
        } catch (Exception e) {
            Utils.errorLog("", e);
        }

        loadData();

        try {
            Utils.log(">>>> On initData.");
        } catch (NullPointerException ne) {
            Utils.errorLog("Null Pointer Exception.", ne);
        } catch (Exception e) {
            Utils.errorLog("Error in getting notification flag data.", e);
        }
    }

    //endregion

    //region Private Methods

    private void loadData() {

        setData(coronaViewModel.getDataByCountry(country));
    }

    private void setData(Corona corona) {

        binding.get().setAboutUs(corona);

        // bind data
        if (!corona.getCountry().equals("")) {
            binding.get().tvCountry.setText(corona.getCountry());
            binding.get().tvCases.setText(String.valueOf(corona.getCases()));
            binding.get().tvTodayCases.setText(String.valueOf(corona.getTodayCases()));
            binding.get().tvDeaths.setText(String.valueOf(corona.getDeaths()));
            binding.get().tvTodayDeaths.setText(String.valueOf(corona.getTodayDeaths()));
            binding.get().tvRecoverd.setText(String.valueOf(corona.getRecovered()));
            binding.get().tvCritcal.setText(String.valueOf(corona.getCritical()));
            binding.get().tvActive.setText(String.valueOf(corona.getActive()));
            binding.get().tvCasesPerOneMillion.setText(String.valueOf(corona.getCasesPerOneMillion()));
            binding.get().tvDeathsPerOneMillion.setText(String.valueOf(corona.getDeathsPerOneMillion()));
            binding.get().tvTest.setText(String.valueOf(corona.getTests()));
            binding.get().tvTestsPerOneMillion.setText(String.valueOf(corona.getTestsPerOneMillion()));

        }


    }

    //endregion

}

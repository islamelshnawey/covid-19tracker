package com.is.covid_19_tracker.ui.worldList;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.is.covid_19_tracker.Config;
import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.data.Resource;
import com.is.covid_19_tracker.data.binding.FragmentDataBindingComponent;
import com.is.covid_19_tracker.data.local.entity.Corona;
import com.is.covid_19_tracker.databinding.FragmentWorldListBinding;
import com.is.covid_19_tracker.ui.base.BaseFragment;
import com.is.covid_19_tracker.ui.base.DataBoundListAdapter;
import com.is.covid_19_tracker.util.AutoClearedValue;
import com.is.covid_19_tracker.util.Utils;
import com.is.covid_19_tracker.viewModel.CoronaViewModel;

import java.util.List;

/**
 * @author Islam Elshnawey
 * @date 4/10/20
 * <p>
 * is.elshnawey@gmail.com
 **/
public class WorldListFragment extends BaseFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private CoronaViewModel coronaViewModel;
    public WorldListAdapter nvAdapter;

    private SearchView searchView;

    @VisibleForTesting
    private AutoClearedValue<FragmentWorldListBinding> binding;
    private AutoClearedValue<WorldListAdapter> adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWorldListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_world_list, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());
        setHasOptionsMenu(true);
        return binding.get().getRoot();

    }

    @Override
    public void onDispatched() {
        if (coronaViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().notificationList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().notificationList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    protected void initUIAndActions() {

        binding.get().notificationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !coronaViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                coronaViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.NOTI_LIST_COUNT;
                                coronaViewModel.offset = coronaViewModel.offset + limit;
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            coronaViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            coronaViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            coronaViewModel.forceEndLoading = false;

            //String deviceToken = pref.getString(Constants.NOTI_TOKEN, "");

            // update live data
            //depositViewModel.setBankListObj(loginUserId, deviceToken, String.valueOf(Config.NOTI_LIST_COUNT), String.valueOf(depositViewModel.offset));

        });
    }

    @Override
    protected void initViewModels() {
        coronaViewModel = new ViewModelProvider(this, viewModelFactory).get(CoronaViewModel.class);
    }

    @Override
    protected void initAdapters() {

        nvAdapter = new WorldListAdapter(dataBindingComponent,
                corona ->
                navigationController.navigateToDetailsActivity(getActivity(), corona.getCountry()),
                this);

        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().notificationList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {
        LoadData();

        try {
            Utils.log(">>>> On initData.");

            //coronaViewModel.token = dataManager.getUser().getToken();

        } catch (NullPointerException ne) {
            Utils.errorLog("Null Pointer Exception.", ne);
        } catch (Exception e) {
            Utils.errorLog("Error in getting notification flag data.", e);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.get().getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.get().getFilter().filter(query);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void LoadData() {

        coronaViewModel.setWorldListObj();

        LiveData<Resource<List<Corona>>> news = coronaViewModel.getWorldListData();

        if (news != null) {
            news.observe(this, listResource -> {
                if (listResource != null) {

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation
                                fadeIn(binding.get().getRoot());

                                // Update the data
                                replaceData(listResource.data);

                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {
                                // Update the data
                                replaceData(listResource.data);
                            }

                            coronaViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            coronaViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.log("Empty Data");

                    if (coronaViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        coronaViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        coronaViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(coronaViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });


    }

    private void replaceData(List<Corona> list) {

        adapter.get().replace(list);
        binding.get().executePendingBindings();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Utils.log("Request code " + requestCode);
        Utils.log("Result code " + resultCode);

    }
}

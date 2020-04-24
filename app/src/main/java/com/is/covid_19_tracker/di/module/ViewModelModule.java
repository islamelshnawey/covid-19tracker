package com.is.covid_19_tracker.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.is.covid_19_tracker.factory.ViewModelFactory;
import com.is.covid_19_tracker.viewModel.CoronaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


/**
 * @author mac
 *
 * The ViewModelModule is used to provide a map of view models through dagger that
 * is used by the ViewModelFactory class.
 *
 *  so basically
 *  We can use the ViewModelModule to define our ViewModels.
 *  We provide a key for each ViewModel using the ViewModelKey class.
 *  Then in our Activity/Fragment, we use the ViewModelFactory class to
 *  inject the corresponding ViewModel. (We will look into more detail at
 *  this when we are creating our Activity).
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MovieListViewModel.class as key,
     * and a Provider that will build a MovieListViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(CoronaViewModel.class)
    protected abstract ViewModel coronaViewModel(CoronaViewModel coronaViewModel);

}
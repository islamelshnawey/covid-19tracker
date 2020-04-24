package com.is.covid_19_tracker.di.module;


import com.is.covid_19_tracker.ui.details.DetailsActivity;
import com.is.covid_19_tracker.ui.details.DetailsFragment;
import com.is.covid_19_tracker.ui.local.LocalFragment;
import com.is.covid_19_tracker.ui.worldList.WorldListActivity;
import com.is.covid_19_tracker.ui.worldList.WorldListFragment;
import com.is.covid_19_tracker.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Since we are using the dagger-android support library,
 * we can make use of Android Injection.
 * The ActivityModule generates AndroidInjector(this is the new dagger-android class which exist in dagger-android framework)
 * for Activities defined in this class.
 * This allows us to inject things into Activities using AndroidInjection.
 * inject(this) in the onCreate() method.
 */
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract DetailsActivity contributeDetailsActivity();

    @ContributesAndroidInjector()
    abstract WorldListFragment contributeBankListFragment();

    @ContributesAndroidInjector()
    abstract DetailsFragment contributePersonalInfoFragment();

    @ContributesAndroidInjector()
    abstract LocalFragment contributeLocalFragment();

    @ContributesAndroidInjector()
    abstract WorldListActivity contributeWorldListActivity();
}
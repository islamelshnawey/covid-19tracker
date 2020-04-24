package com.is.covid_19_tracker.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.is.covid_19_tracker.util.Connectivity;
import com.is.covid_19_tracker.util.NavigationController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Islam Elshnawey
 * @date 2020-03-28
 * <p>
 * is.elshnawey@gmail.com
 **/
@Module
public class AppModule {

    @Singleton
    @Provides
    Connectivity provideConnectivity(Application app) {
        return new Connectivity(app);
    }

    @Singleton
    @Provides
    NavigationController provideNavigationController() {
        return new NavigationController();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }

}

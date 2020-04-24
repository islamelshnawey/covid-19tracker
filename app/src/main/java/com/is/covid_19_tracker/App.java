package com.is.covid_19_tracker;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.is.covid_19_tracker.di.AppInjector;
import com.is.covid_19_tracker.di.module.ApiModule;
import com.is.covid_19_tracker.di.module.DbModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


/**
 * Created by Islam Elshnawey on 4/9/20.
 * <p>
 * is.elshnawey@gmail.com
 **/
public class App extends MultiDexApplication implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppInjector.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

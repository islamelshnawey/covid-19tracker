package com.is.covid_19_tracker.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.is.covid_19_tracker.data.AppDatabase;
import com.is.covid_19_tracker.data.local.dao.CoronaDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    /**
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "corona.db")
                .allowMainThreadQueries().build();
    }


    /**
     * We need the Dao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */

    @Provides
    @Singleton
    CoronaDao provideCoronaDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.coronaDao();
    }

}

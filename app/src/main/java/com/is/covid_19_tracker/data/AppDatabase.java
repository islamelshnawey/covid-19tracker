package com.is.covid_19_tracker.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.is.covid_19_tracker.data.local.dao.CoronaDao;
import com.is.covid_19_tracker.data.local.entity.Corona;


@Database(entities = {
         Corona.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoronaDao coronaDao();

}
package com.is.covid_19_tracker.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.is.covid_19_tracker.data.Resource;
import com.is.covid_19_tracker.data.local.entity.Corona;

import java.util.List;

/**
 * @author Islam Elshnawey
 * @date 4/9/20
 * <p>
 * is.elshnawey@gmail.com
 **/
@Dao
public interface CoronaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Corona corona);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Corona> coronaList);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Corona corona);

    @Query("SELECT * FROM Corona")
    LiveData<List<Corona>> getCoronaList();

    @Query("SELECT * FROM Corona WHERE country = :country ")
    Corona  getCoronaByCountry(String country);

    @Query("SELECT count(*) FROM  Corona")
    int getCoronaListCount();

    @Query("DELETE FROM Corona")
    void deleteCoronasList();
}

package com.barry.baseandroidarchitecture.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(DataModel data);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<DataModel> data);

    @Query("DELETE FROM data_table")
    void deleteAll();

    @Delete
    void delete(DataModel data);

    @Query("Select * from data_table Order By id Desc")
    LiveData<List<DataModel>> getAll();
}

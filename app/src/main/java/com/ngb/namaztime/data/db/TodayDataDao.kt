package com.ngb.namaztime.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ngb.namaztime.data.db.entities.TODAY_DATA_ID
import com.ngb.namaztime.data.db.entities.TodayData

@Dao
interface TodayDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(todayData: TodayData)

    @Query("SELECT * FROM today_data WHERE ID = $TODAY_DATA_ID")
    fun getTodaydata() : LiveData<TodayData>

}
package com.ngb.namaztime.data.db.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ngb.namaztime.data.response.Date
import com.ngb.namaztime.data.response.Meta
import com.ngb.namaztime.data.response.Timings

const val TODAY_DATA_ID = 0

@Entity(tableName = "today_data")
data class TodayData(
    @Embedded(prefix = "date_")
    val date: Date,
    @Embedded(prefix = "meta_")
    val meta: Meta,
    @Embedded(prefix = "timings_")
    val timings: Timings
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = TODAY_DATA_ID
}
package com.ngb.namaztime.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ngb.namaztime.data.db.entities.TodayData

@Database(entities = [TodayData::class], version = 1, exportSchema = false)
abstract class PrayerTimeDatabase : RoomDatabase() {

    abstract fun todayDataDao(): TodayDataDao

    companion object {
        @Volatile private var instance: PrayerTimeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PrayerTimeDatabase::class.java, "prayertimedatabasee.db")
                .build()
    }

}
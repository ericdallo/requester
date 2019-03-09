package com.gregcodes.requester.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gregcodes.requester.Request
import com.gregcodes.requester.RequestRepository

@Database(entities =  [ Request::class ], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRequestRepository(): RequestRepository

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "requester.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
package com.example.declarative.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.declarative.model.TVShow

@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getTvShowDao():TVShowDao

    companion object{
        private var DB_INSTANCE:AppDatabase? = null

        fun getAppDBInstance(context: Context):AppDatabase{
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DB_TV_SHOWS"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}
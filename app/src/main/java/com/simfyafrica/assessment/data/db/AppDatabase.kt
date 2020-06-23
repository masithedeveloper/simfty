package com.simfyafrica.assessment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.simfyafrica.assessment.data.model.Cat

@Database(entities = [Cat::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "cats.db"
        const val VERSION = 1

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

    abstract fun catsDao(): CatsDao
}
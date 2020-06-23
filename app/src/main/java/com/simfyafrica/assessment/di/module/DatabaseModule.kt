package com.simfyafrica.assessment.di.module

import android.app.Application
import androidx.room.Room
import com.simfyafrica.assessment.data.db.AppDatabase
import com.simfyafrica.assessment.data.db.CatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): CatsDao {
        return appDataBase.catsDao()
    }
}
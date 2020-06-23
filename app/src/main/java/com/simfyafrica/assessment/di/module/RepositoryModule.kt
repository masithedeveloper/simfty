package com.simfyafrica.assessment.di.module

import com.simfyafrica.assessment.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import com.simfyafrica.assessment.data.CatsRepository
import com.simfyafrica.assessment.data.CatsRepositoryImpl
import com.simfyafrica.assessment.data.network.WebService
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCatsRepository(webService: WebService, database: AppDatabase): CatsRepository {
        return CatsRepositoryImpl(webService, database)
    }
}
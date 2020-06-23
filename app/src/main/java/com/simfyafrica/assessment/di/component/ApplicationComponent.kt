package com.simfyafrica.assessment.di.component

import android.app.Application
import com.simfyafrica.assessment.application.App
import com.simfyafrica.assessment.di.builder.ActivityBuilder
import com.simfyafrica.assessment.di.module.ContextModule
import com.simfyafrica.assessment.di.module.DatabaseModule
import com.simfyafrica.assessment.di.module.NetworkModule
import com.simfyafrica.assessment.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ActivityBuilder::class,
        ContextModule::class,
        NetworkModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
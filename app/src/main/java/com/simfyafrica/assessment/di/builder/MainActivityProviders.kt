package com.simfyafrica.assessment.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.simfyafrica.assessment.ui.cats.CatsFragment
import com.simfyafrica.assessment.ui.cats.CatDetailsFragment

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideCatsFragment(): CatsFragment

    @ContributesAndroidInjector
    abstract fun provideCatDetailsFragment(): CatDetailsFragment

}
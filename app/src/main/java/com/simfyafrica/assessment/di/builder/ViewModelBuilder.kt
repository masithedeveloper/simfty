package com.simfyafrica.assessment.di.builder

import androidx.lifecycle.ViewModelProvider
import com.simfyafrica.assessment.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory)
            : ViewModelProvider.Factory
}
package com.simfyafrica.assessment.di.builder

import androidx.lifecycle.ViewModel
import com.simfyafrica.assessment.di.qualifier.ViewModelKey
import com.simfyafrica.assessment.ui.cats.CatDetailsViewModel
import com.simfyafrica.assessment.ui.cats.CatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatsViewModel::class)
    abstract fun bindCatsViewModel(catsViewModel: CatsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatDetailsViewModel::class)
    abstract fun bindCatDetailsViewModel(catDetailsViewModel: CatDetailsViewModel): ViewModel

}
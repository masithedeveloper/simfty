package com.simfyafrica.assessment.ui.cats

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.simfyafrica.assessment.R
import com.simfyafrica.assessment.data.CatsRepository
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.data.network.ApiError
import com.simfyafrica.assessment.di.module.NetworkModule
import com.simfyafrica.assessment.ui.base.BaseViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CatsDetailsViewModel"

class CatDetailsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    application: Application
) : BaseViewModel(application) {

    val catData: MutableLiveData<Cat> by lazy { MutableLiveData<Cat>() }
    val error: MutableLiveData<ApiError>  by lazy { MutableLiveData<ApiError>() }

    fun getCatDetails(id: String, position: Int) {
        viewModelScope.launch {
            val cat = catsRepository.geACachedCat(id)
            if(cat.value != null){
                catData.setValue(cat.value)
            }
            else{
                fetchACatDetails(id, position)
            }
        }
    }

    private fun fetchACatDetails(id: String, position: Int) {
        catsRepository.fetchACat(id,
            { cat ->
                if(cat != null){
                    cat.title = "Image $position"
                    cat.description = String.format(context.resources.getString(R.string.cat_description), cat.title)
                    catsRepository.insertCat(cat).also { compositeDisposable.add(it) }
                    catData.postValue(cat)
                }
            }, {
                Log.d(TAG, "getCatsData.error() called with: $it")
                error.value = it
            }, {
                Log.d(TAG, "getCatsData.terminate() called")
            }
        ).also { compositeDisposable.add(it) }
    }
}

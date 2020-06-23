package com.simfyafrica.assessment.ui.cats

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.simfyafrica.assessment.R
import com.simfyafrica.assessment.data.CatsRepository
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.data.network.ApiError
import com.simfyafrica.assessment.di.module.NetworkModule
import com.simfyafrica.assessment.ui.base.BaseViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CatsViewModel"

class CatsViewModel @Inject constructor(private val catsRepository: CatsRepository,
                                        application: Application
) : BaseViewModel(application) {

    val catsData: MutableLiveData<MutableList<Cat>> by lazy { MutableLiveData<MutableList<Cat>>() }
    val error: MutableLiveData<ApiError>  by lazy { MutableLiveData<ApiError>() }

    init {
        getCatsList()
    }

    private fun getCatsList() {
        viewModelScope.launch {
            val cats = catsRepository.getAllCachedCats()
            if(!cats.value.isNullOrEmpty()){
                catsData.setValue(cats.value as MutableList<Cat>?)
            }
            else{
                Glide.get(context).clearMemory()
                fetchAllCats()
            }
        }
    }

    private fun fetchAllCats() {
//        NetworkModule.host = "thecatapi.com/api/images"
//        NetworkModule.scheme = "http://"

        catsRepository.fetchCats(
            { cats ->
                catsData.postValue(cats)
                if (!cats.isNullOrEmpty()) {
                    for ((titleCount, cat) in cats.withIndex()) {
                        cat.title = "Image $titleCount"
                        cat.description = String.format(context.resources.getString(R.string.cat_description), cat.title)
                        catsRepository.insertCat(cat).also { compositeDisposable.add(it) }
                    }
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

package com.simfyafrica.assessment.data

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.data.network.ApiError

interface CatsRepository {
    fun fetchCats(
        success: (MutableList<Cat>?) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun fetchACat(id: String , success: (Cat?) -> Unit,
                  failure: (ApiError) -> Unit = {},
                  terminate: () -> Unit = {}
    ): Disposable

    fun insertCat(cat: Cat): Disposable

    fun geACachedCat(id: String): LiveData<Cat>

    fun getAllCachedCats(): LiveData<List<Cat>>
}
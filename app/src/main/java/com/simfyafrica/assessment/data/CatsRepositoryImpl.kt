package com.simfyafrica.assessment.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.JsonArray
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.simfyafrica.assessment.data.db.AppDatabase
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.data.network.ApiError
import com.simfyafrica.assessment.data.network.WebService
import javax.inject.Inject
import kotlin.Result.Companion.success

const val TAG: String = "CatsRepository"

class CatsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val appDatabase: AppDatabase
) : CatsRepository {

    override fun fetchCats(
        success: (MutableList<Cat>?) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return webService.getJSONCats().subscribeOn(Schedulers.io()).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                success(it.toMutableList())
            }
    }

    override fun fetchACat(
        id: String,
        success: (Cat?) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return webService.getAJSONCat(id).subscribeOn(Schedulers.io()).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                success(it)
            }
    }

    override fun insertCat(cat: Cat): Disposable {
        return appDatabase
            .catsDao()
            .insertCat(cat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "Cat added to database ,  id : ${cat.description}")
            }
    }

    override fun geACachedCat(id: String): LiveData<Cat> {
        return appDatabase.catsDao().getACat(id)
    }

    override fun getAllCachedCats(): LiveData<List<Cat>> {
        return appDatabase.catsDao().getAllCats()
    }
}

//webService.getXMLCats().subscribeOn(Schedulers.io()).toObservable(),
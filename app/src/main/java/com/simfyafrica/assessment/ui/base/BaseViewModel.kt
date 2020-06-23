package com.simfyafrica.assessment.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    protected val context = getApplication<Application>().applicationContext
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
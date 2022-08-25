package com.example.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> Fragment.viewModels(
    crossinline viewModelProducer: () -> VM
): Lazy<VM> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <VM : ViewModel> create(modelClass: Class<VM>) = viewModelProducer() as VM
        }
        val viewModelProvider = ViewModelProvider(this, factory)
        viewModelProvider[VM::class.java]
    }
}

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

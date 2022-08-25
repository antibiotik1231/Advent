package com.example.common.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.common.mvvm.CommandsLiveData
import java.util.*

inline fun <reified T> Fragment.argument(key: String, defaultValue: T): Lazy<T> =
    lazy { arguments?.get(key) as? T ?: defaultValue }

// Why use getViewLifecycleOwner? See https://github.com/android/architecture-components-samples/issues/47
inline fun <reified T : Any, reified L : LiveData<T>> Fragment.observe(
    liveData: L,
    noinline block: (T) -> Unit
) {
    liveData.observe(this.viewLifecycleOwner, Observer { it?.let { block.invoke(it) } })
}

inline fun <reified T : Any, reified L : CommandsLiveData<T>> Fragment.observe(
    liveData: L,
    noinline block: (T) -> Unit
) {
    observe(this.viewLifecycleOwner, liveData, block)
}


inline fun <reified T : Any, reified L : CommandsLiveData<T>> observe(
    lifecycleOwner: LifecycleOwner,
    liveData: L,
    noinline block: (T) -> Unit
) {
    liveData.observe(lifecycleOwner, Observer<LinkedList<T>> { commands ->
        if (commands == null) {
            return@Observer
        }
        var command: T?
        do {
            command = commands.poll()
            if (command != null) {
                block.invoke(command)
            }
        } while (command != null)
    })
}
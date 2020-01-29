package com.spm.androidtesting.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
class SingleEventEmitter<T : Any> {
    private val list: MutableSet<LifecycleObserverWrapper<T>> = mutableSetOf()
    private val listener = object : LifecycleObserverWrapper.OnStateChangedListener<T> {
        override fun onStateChanged(state: WrapperState<T>) {
            when (state) {
                is WrapperState.WrapperAdded -> list.add(state.wrapper)
                is WrapperState.WrapperRemoved -> list.remove(state.wrapper)
            }
        }
    }
    var value: T? = null
        set(value) {
            field = value
            value?.apply {
                list.forEach { it.onValueChanged(value) }
            }
        }
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun observersCount(): Int = list.size
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun hasObservers(): Boolean = list.size != 0
    fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        LifecycleObserverWrapper(owner, observer, listener)
    }
}
private class LifecycleObserverWrapper<T>(
    private val owner: LifecycleOwner,
    private val observer: Observer<T>,
    private val listener: OnStateChangedListener<T>
) : LifecycleObserver {
    interface OnStateChangedListener<T> {
        fun onStateChanged(state: WrapperState<T>)
    }
    init {
        owner.lifecycle.addObserver(this)
        listener.onStateChanged(WrapperState.WrapperAdded(this))
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        owner.lifecycle.removeObserver(this)
        listener.onStateChanged(WrapperState.WrapperRemoved(this))
    }
    fun onValueChanged(value: T) = observer.onChanged(value)
}
private sealed class WrapperState<T> {
    data class WrapperAdded<T>(val wrapper: LifecycleObserverWrapper<T>) : WrapperState<T>()
    data class WrapperRemoved<T>(val wrapper: LifecycleObserverWrapper<T>) : WrapperState<T>()
}
package codemakers.daggermvvm.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ana Marin on 13/10/2017.
 */
class LifeDisposable(owner: LifecycleOwner): LifecycleObserver{

    init {
        owner.lifecycle.addObserver(this)
    }

    val dis:CompositeDisposable = CompositeDisposable()

    infix fun add(disposable: Disposable){
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearDisposable(){
        dis.clear()
    }
}

fun <T> Flowable<T>.applySchedulers(): Flowable<T> = compose {
            it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
            it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
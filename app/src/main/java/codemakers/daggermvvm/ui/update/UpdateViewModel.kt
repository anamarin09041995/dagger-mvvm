package codemakers.daggermvvm.ui.update

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.util.applySchedulers
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromCallable
import io.reactivex.internal.operators.observable.ObservableFromFuture
import javax.inject.Inject

/**
 * Created by Ana Marin on 14/10/2017.
 */
class UpdateViewModel @Inject constructor(val dao: TodoDao): ViewModel(){

    fun update(tarea: Tarea) : Observable<Unit>{
        return ObservableFromCallable{ dao.update(tarea)}
                .applySchedulers()
    }
}
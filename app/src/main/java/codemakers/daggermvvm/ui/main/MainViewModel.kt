package codemakers.daggermvvm.ui.main

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.util.applySchedulers
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromCallable
import javax.inject.Inject


/**
 * Created by Ana Marin on 14/10/2017.
 */
class MainViewModel @Inject constructor(val dao: TodoDao): ViewModel(){

    fun getAllTodo(): Flowable<List<Tarea>> = dao.all()
            .applySchedulers()

    fun deshacer(tarea: Tarea): Observable<Unit> =
            ObservableFromCallable { dao.insert(tarea) }
                    .applySchedulers()

    fun eliminar(tarea: Tarea): Observable<Unit> =
            ObservableFromCallable { dao.delete(tarea) }
                    .applySchedulers()

    }

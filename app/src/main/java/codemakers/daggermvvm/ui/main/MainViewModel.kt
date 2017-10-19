package codemakers.daggermvvm.ui.main

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.util.applySchedulers
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by Ana Marin on 14/10/2017.
 */
class MainViewModel @Inject constructor(val dao: TodoDao): ViewModel(){

    fun getAllTodo(): Flowable<List<Tarea>> = dao.all()
            .applySchedulers()

}
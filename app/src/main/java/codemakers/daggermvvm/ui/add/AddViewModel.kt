package codemakers.daggermvvm.ui.add

import android.arch.lifecycle.ViewModel
import codemakers.daggermvvm.data.dao.TodoDao
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.util.applySchedulers
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by Ana Marin on 14/10/2017.
 */

class AddViewModel @Inject constructor(val dao: TodoDao): ViewModel(){

    fun addTarea(nombre: String, descripcion: String): Observable<Unit>{
        return Observable.fromCallable { dao.insert(Tarea(null, nombre, descripcion, Date()))}
                .applySchedulers()
    }

}
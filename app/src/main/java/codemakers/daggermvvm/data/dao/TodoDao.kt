package codemakers.daggermvvm.data.dao

import android.arch.persistence.room.*
import codemakers.daggermvvm.data.model.Tarea
import io.reactivex.Flowable

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Dao
interface TodoDao {

    @Insert
    fun insert(tarea: Tarea)

    @Update
    fun update(tarea: Tarea)

    @Delete
    fun delete(tarea: Tarea)

    @Query("SELECT * FROM tarea")
    fun all(): Flowable<List<Tarea>>
}
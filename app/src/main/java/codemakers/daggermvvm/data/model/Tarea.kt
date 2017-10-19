package codemakers.daggermvvm.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Ana Marin on 13/10/2017.
 */
@SuppressLint("ParcelCreator")
@Entity
class Tarea(@PrimaryKey var id: Long?,
            var name:String?,
            var description:String?,
            var date:Date
            ) {

    @Ignore
    constructor(title: String, description: String?) : this(null, title, description, Date())

}
package codemakers.daggermvvm.data.model

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import codemakers.daggermvvm.R.id.nombre
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Ana Marin on 13/10/2017.
 */
@SuppressLint("ParcelCreator")
@Entity
@Parcelize
class Tarea(@PrimaryKey var id: Long?,
            var name:String?,
            var description:String?,
            var date:Date
            ): Parcelable {

    @Ignore
    constructor(title: String, description: String?) : this(null, title, description, Date())

    companion object: Parceler<Tarea>{
        override fun create(parcel: Parcel): Tarea = Tarea(
                parcel.readLong(),
                parcel.readString(),
                parcel.readString(),
                parcel.readSerializable() as Date)

        override fun Tarea.write(parcel: Parcel, flags: Int) {
            parcel.writeLong(id!!)
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeSerializable(date)
        }

    }
}
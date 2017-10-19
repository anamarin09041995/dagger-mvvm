package codemakers.daggermvvm.util

import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import io.reactivex.Observable
import org.jetbrains.anko.toast

/**
 * Created by Ana Marin on 13/10/2017.
 */
fun ViewGroup.inflate(@LayoutRes layout:Int) = LayoutInflater.from(context).inflate(layout, this, false)

fun EditText.text():String = text.toString()

fun AppCompatActivity.validateForm(message: Int,
                                   vararg fields: String) : Observable<List<String>>
        = Observable.create<List<String>>{
    if(fields.contains("")) toast(message)
    else it.onNext(fields.toList())
    it.onComplete()
}


fun AppCompatActivity.snackBarAction(container: View,
                                     message: Int,
                                     button: Int,
                                     obj: Any) : Observable<Any>
        = Observable.create<Any> { emitter->
    Snackbar.make(container,message,Snackbar.LENGTH_LONG)
            .setAction(button, {
                emitter.onNext(obj)
                emitter.onComplete()
            } )
            .show()
}




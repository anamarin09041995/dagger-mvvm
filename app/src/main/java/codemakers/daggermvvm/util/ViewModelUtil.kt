package codemakers.daggermvvm.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Singleton
class AppViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>,
                                              @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: throw Throwable("Modelo de clase desconocido $modelClass")
        return creator.get() as T
    }
}
package codemakers.daggermvvm.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import codemakers.daggermvvm.ui.add.AddViewModel
import codemakers.daggermvvm.ui.main.MainViewModel
import codemakers.daggermvvm.ui.update.UpdateViewModel
import codemakers.daggermvvm.util.AppViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by Ana Marin on 14/10/2017.
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    abstract fun bindAddViewModel(viewModel: AddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateViewModel::class)
    abstract fun bindUpdateViewModel(viewModel: UpdateViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory) : ViewModelProvider.Factory

}
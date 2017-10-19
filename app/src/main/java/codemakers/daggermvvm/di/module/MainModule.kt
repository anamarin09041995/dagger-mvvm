package codemakers.daggermvvm.di.module

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import codemakers.daggermvvm.di.ActivityScope
import codemakers.daggermvvm.ui.adapter.TareaAdapter
import codemakers.daggermvvm.ui.main.MainActivity
import codemakers.daggermvvm.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Module
class MainModule{

    @ActivityScope
    @Provides
    fun provideAdapter(): TareaAdapter = TareaAdapter()

    @ActivityScope
    @Provides
    fun providesViewModel(activity: MainActivity, factory: ViewModelProvider.Factory): MainViewModel
            = ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
}
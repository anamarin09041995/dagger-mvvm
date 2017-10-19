package codemakers.daggermvvm.di

import codemakers.daggermvvm.di.module.MainModule
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.ui.main.MainActivity
import codemakers.daggermvvm.ui.update.UpdateActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ActivityScope

@Module
abstract class ActivityBuilders{

    //Subcomponentes

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAAddActivity(): AddActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindUpdateActivity(): UpdateActivity
}
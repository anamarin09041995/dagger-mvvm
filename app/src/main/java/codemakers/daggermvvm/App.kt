package codemakers.daggermvvm

import android.app.Activity
import android.app.Application
import codemakers.daggermvvm.di.component.AppComponent
import codemakers.daggermvvm.di.component.DaggerAppComponent
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Ana Marin on 13/10/2017.
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>
            = injector


}
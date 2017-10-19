package codemakers.daggermvvm.di.module

import android.app.Activity
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import codemakers.daggermvvm.data.AppDataBase
import codemakers.daggermvvm.data.dao.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ana Marin on 13/10/2017.
 */
@Module
class AppModule{

    //Agregar retrofit aqui

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "todo.db")
                    .build()

    @Singleton
    @Provides
    fun provideTodoDao(appDataBase: AppDataBase): TodoDao = appDataBase.todoDao()
}
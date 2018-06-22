package com.example.emilin.portfolio.di.module

import android.app.Application
import android.content.Context
import com.example.emilin.pokemonapp.injection.scope.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application : Application) {
    @Provides
    internal fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    internal fun provideApplicationContext(): Context = application
}
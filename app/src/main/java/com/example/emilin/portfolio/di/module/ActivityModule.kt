package com.example.emilin.portfolio.di.module

import android.app.Activity
import android.content.Context
import com.example.emilin.pokemonapp.injection.scope.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (val activity: Activity){
    @Provides
    internal fun provideActivity(): Activity = activity

    @Provides
    @ActivityContext
    internal fun provideActivityContext(): Context = activity
}

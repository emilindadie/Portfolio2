package com.example.emilin.portfolio.di.module

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import com.example.emilin.pokemonapp.injection.scope.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(val fragment : Fragment) {

    @Provides
    internal fun provideFragment(): Fragment = fragment

    @Provides
    @ActivityContext
    internal fun provideFragmentContext(): Context? = fragment.context
}
package com.example.emilin.portfolio.di.component

import android.app.Application
import android.content.Context
import com.example.emilin.pokemonapp.injection.scope.ApplicationContext
import com.example.emilin.portfolio.app.PortFolio
import com.example.emilin.portfolio.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


//Design pattern qui indique que cette interface sera ancier qu'une seule fois
@Singleton
//Cette annotation definis ou pourront être injecté nos différents éléments qui sont dans nos module
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    @get:ApplicationContext
    val context: Context
    val application: Application
    //On pourra injecté des éléménts dans notre application
    fun inject(portFolio: PortFolio)
}
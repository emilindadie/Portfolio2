package com.example.emilin.portfolio.app

import android.app.Application
import com.example.emilin.portfolio.di.component.ApplicationComponent
import com.example.emilin.portfolio.di.component.DaggerApplicationComponent
import com.example.emilin.portfolio.di.module.ApplicationModule

class PortFolio : Application(){
    companion object {
        lateinit var component: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }
}
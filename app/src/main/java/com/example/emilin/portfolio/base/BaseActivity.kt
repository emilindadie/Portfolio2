package com.example.emilin.portfolio.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.emilin.portfolio.app.PortFolio
import com.example.emilin.portfolio.di.component.ActivityComponent
import com.example.emilin.portfolio.di.component.DaggerActivityComponent
import com.example.emilin.portfolio.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity(){
    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initInjector()
    }

    abstract fun getLayoutResId(): Int


    private fun initInjector() {
        component = DaggerActivityComponent.builder()
                .applicationComponent(PortFolio.component)
                .activityModule(ActivityModule(this))
                .build()
    }
}
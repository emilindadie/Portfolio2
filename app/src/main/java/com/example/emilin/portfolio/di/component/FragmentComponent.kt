package com.example.emilin.portfolio.di.component

import com.example.emilin.portfolio.di.module.ActivityModule
import com.example.emilin.portfolio.di.module.FragmentModule
import com.example.emilin.portfolio.di.scope.PerActivity
import dagger.Component


@PerActivity
//Cette annotation definis ou pourront être injecté nos différents éléments qui sont dans nos module
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
}
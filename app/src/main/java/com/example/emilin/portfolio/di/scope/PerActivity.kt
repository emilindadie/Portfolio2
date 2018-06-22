package com.example.emilin.portfolio.di.scope

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

//Chaque object avec cette annotation auront une durée de vie égal à l'activité ayant instancié l'object
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
package com.example.emilin.portfolio.tracker

import android.app.Activity
import android.content.Context
import android.location.Location

interface GpsTraker {
    fun getCurrentLocation() : Location?
    fun setContext(context: Activity)
}
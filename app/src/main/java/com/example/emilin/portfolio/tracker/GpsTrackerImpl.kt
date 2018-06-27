package com.example.emilin.portfolio.tracker

import android.Manifest
import android.app.Activity
import android.location.Location
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log
import android.location.LocationListener
import android.os.Bundle


class GpsTrackerImpl() : GpsTraker, LocationListener{

    var isGPSEnabled = false
    // flag for network status
    var isNetworkEnabled = false
    // flag for GPS status
    var canGetLocation = false
    lateinit var context: Context

    var location: Location? = null

    var latitude: Double = 0.toDouble() // latitude
    var longitude: Double = 0.toDouble() // longitude
    override fun getCurrentLocation(): Location? {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null
        }
        try {
            val lm = context.getApplicationContext().getSystemService(LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            // getting network status
            isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                this.canGetLocation = true
                if (isNetworkEnabled) {
                    lm.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            6000,
                            10F, this)
                    Log.d("Network", "Network")
                    if (lm != null) {
                        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.getLatitude()
                            longitude = location!!.getLongitude()
                        }
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        lm.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                6000,
                                10F, this)
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (lm != null) {
                            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.getLatitude()
                                longitude = location!!.getLongitude()
                            }
                        }
                    }
                }
                return location
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onLocationChanged(location: Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun setContext(context: Activity) {
        this.context = context
    }
}
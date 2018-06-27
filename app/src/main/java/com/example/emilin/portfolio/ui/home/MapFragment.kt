package com.example.emilin.portfolio.ui.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import com.example.emilin.portfolio.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.*
import android.widget.Toast
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.R.id.center_current_position
import com.example.emilin.portfolio.tracker.GpsTraker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.android.synthetic.main.fragment_map.*
import javax.inject.Inject

class MapFragment : BaseFragment(), OnMapReadyCallback, LocationListener{
    override fun onchildHasMapReadyCallBack(): OnMapReadyCallback? {
        return this
    }

    val MY_PERMISSIONS_REQUEST_LOCATION = 1
    lateinit var mMap:  GoogleMap
    lateinit var  lastLocation: Location

    @Inject
    lateinit var gpsTraker: GpsTraker

    fun newInstance(page: Int, title: String): MapFragment {
        val fragment = MapFragment()
        val args = Bundle()
        args.putInt(super.PAGE, page)
        args.putString(super.TITLE, title)
        fragment.setArguments(args)
        return fragment
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gpsTraker.setContext(activity!!)
        center_current_position.setOnClickListener {
            showPosition()
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_map

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            MY_PERMISSIONS_REQUEST_LOCATION ->
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        showPosition()
                    }
                }
        }
    }

    override fun onMapReady(map : GoogleMap?) {
        mMap = map!!

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            activity, R.raw.mapstyle))

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }


        showPosition()
    }

    @SuppressLint("MissingPermission")
    fun centerMapOnLocation(location: Location, title: String){
        mMap.clear()
        mMap.isMyLocationEnabled = true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12F))
    }

    @SuppressLint("MissingPermission")
    override fun onLocationChanged(location: Location?) {
        mMap.clear()
        mMap.isMyLocationEnabled = true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location!!.latitude, location.longitude), 12F))
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    fun showPosition(){
        if(Build.VERSION.SDK_INT < 23){
        }else{
            if(ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                lastLocation = gpsTraker.getCurrentLocation()!!
                centerMapOnLocation(lastLocation, "current position")
            }else{
                val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(activity!!, permissions,1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        // Do something that differs the Activity's menu here
        inflater!!.inflate(R.menu.map_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search-> {

                return false
            }
            else -> {
            }
        }
        return false
    }
}
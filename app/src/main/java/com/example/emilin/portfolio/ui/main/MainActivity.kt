package com.example.emilin.portfolio.ui.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{


    val MY_PERMISSIONS_REQUEST_LOCATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_cv_button?.setOnClickListener(this)
        github_image?.setOnClickListener(this)
        linkedin_image?.setOnClickListener(this)
    }

    fun navigateToMeActivity(context: Context){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        askLocationPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            MY_PERMISSIONS_REQUEST_LOCATION ->
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(this, "Position activé", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Position refusé", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    fun askLocationPermission(){
        if(Build.VERSION.SDK_INT < 23){
        }else{
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Position déja activé", Toast.LENGTH_SHORT).show()
            }else{
                val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(this, permissions,1)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.github_image-> {
                val url :  String = getString(R.string.github_url)
                navigateWebViewActivity(this@MainActivity, url)
            }

            R.id.linkedin_image->{
                val url :  String = getString(R.string.linkedin_url)
                navigateWebViewActivity(this@MainActivity, url)
            }
            R.id.view_cv_button->{
                navigateToMeActivity(this)

            }
            else -> {
            }
        }
    }

    fun navigateWebViewActivity(context: Context, url: String){
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}




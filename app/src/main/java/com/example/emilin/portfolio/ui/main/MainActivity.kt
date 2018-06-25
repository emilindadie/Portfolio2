package com.example.emilin.portfolio.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(){

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topLinear.setBackgroundColor(R.color.primary_material_light)
        view_cv_button.setOnClickListener {
            navigateToMeActivity(this)
        }
    }

    fun navigateToMeActivity(context: Context){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }
}



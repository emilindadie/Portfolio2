
package com.example.emilin.portfolio.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.extension.loadUrl
import com.example.emilin.portfolio.model.Diplome
import java.util.ArrayList
import android.content.res.TypedArray
import android.graphics.Color
import android.widget.Toast
import com.example.emilin.portfolio.R.array.colorDiplome

class DiplomeAdapter(val context: Context, val onItemClick: OnItemClick): RecyclerView.Adapter<DiplomeAdapter.ViewHolder>() {

    var diplomesList : List<Diplome> = ArrayList()

    companion object {
        var mClickListener: OnItemClick? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.diplomes_items, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return diplomesList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType", "Recycle")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mClickListener = onItemClick
        val currentDiplome = diplomesList[position]
        holder?.diplome_title?.text = currentDiplome.name
        holder?.diplome_image?.loadUrl(currentDiplome.picture_url)

        val arrayOfStrings = context.getResources().getStringArray(R.array.colorDiplome) // one, two
        holder?.diplome_card?.setBackgroundColor(Color.parseColor(arrayOfStrings[position]))
        holder?.itemView?.setOnClickListener{
            onItemClick.click(diplomesList[position], holder.adapterPosition)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val diplome_card = itemView?.findViewById<CardView>(R.id.diplome_card)
        val diplome_image = itemView?.findViewById<ImageView>(R.id.diplome_item_image)
        val diplome_title = itemView?.findViewById<TextView>(R.id.diplome_item_title)
    }

    interface OnItemClick {
        fun click(diplome: Diplome, position: Int)
    }

    fun loadDiplomes(diplomeList : List<Diplome>){
        this.diplomesList = diplomeList
    }
}
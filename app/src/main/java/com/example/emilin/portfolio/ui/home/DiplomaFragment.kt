package com.example.emilin.portfolio.ui.home


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.adapter.DiplomeAdapter
import com.example.emilin.portfolio.base.BaseFragment
import com.example.emilin.portfolio.model.Diplome
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_diploma.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class DiplomaFragment : BaseFragment() {

    lateinit var diplomeAdapter: DiplomeAdapter

    override fun onchildHasMapReadyCallBack(): OnMapReadyCallback? {
        return null
    }

    override fun getLayoutResId(): Int = R.layout.fragment_diploma

    fun newInstance(page: Int, title: String): DiplomaFragment {
        val fragment = DiplomaFragment()
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

        setupRecyclerView()
    }

    fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(activity, 2)

        diplome_recyclerView.layoutManager = layoutManager
        diplome_recyclerView.setHasFixedSize(true)

        diplomeAdapter = DiplomeAdapter(context!!, object : DiplomeAdapter.OnItemClick {
            override fun click(pokemon: Diplome, position: Int) {

            }
        })

        loadDiplomes()
        diplome_recyclerView.adapter = diplomeAdapter
    }


    fun loadDiplomes(){
        val diplomesList : MutableList<Diplome> = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val diplomeArray = obj.getJSONArray("diplomes")

            for (i in 0 until diplomeArray.length()) {
                var diplomeObject = diplomeArray.getJSONObject(i)
                diplomesList.add(Diplome(diplomeObject.getInt("id"), diplomeObject.getString("name"), diplomeObject.getString("picture_url"),
                        diplomeObject.getString("full_name"), diplomeObject.getString("school"), diplomeObject.getString("city"),
                        diplomeObject.getString("month"), diplomeObject.getString("year")))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        diplomeAdapter.loadDiplomes(diplomesList)
    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val `is` = context?.getAssets()?.open("diplome.json")
            val size = `is`?.available()
            val buffer = ByteArray(size!!)
            `is`?.read(buffer)
            `is`?.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}

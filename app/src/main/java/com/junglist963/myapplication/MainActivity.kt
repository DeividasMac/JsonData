package com.junglist963.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.junglist963.myapplication.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

private  var list : MutableList<Model> = ArrayList()
private val fragment = Main_Fragment()
private lateinit var binding: ActivityMainBinding
private var tempList: MutableList<Model> = ArrayList()



class MainActivity : AppCompatActivity( ), ClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataWithGson()
        tempList.addAll(list)

        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = Adapter(this, tempList,this)

    }
    override fun onItemClicked(model: Model, bundle: Bundle) {
       supportFragmentManager.beginTransaction().apply {
           fragment.i = bundle.getString("img")
           fragment.x = bundle.getInt("position")
           fragment.listModel = tempList
           replace(R.id.frameFragment,fragment)
           addToBackStack(null)
           commit()
       }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search something!"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    list.forEach(){
                        if (it.urls.lowercase(Locale.getDefault()).contains(searchText)){
                            tempList.add(it)
                        }
                    }
                    binding.rv.adapter!!.notifyDataSetChanged()
                }else{
                    tempList.clear()
                    tempList.addAll(list)
                    binding.rv.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)

    }

    fun getDataWithGson(){
    var gson = Gson()
    var jsonModel = gson.fromJson(assets.open("dog_urls.json").bufferedReader().use { it.readText() }, JsonModel::class.java)

    jsonModel.urls.forEach(){
        list.add(Model(urls = it))
    }
    }
}


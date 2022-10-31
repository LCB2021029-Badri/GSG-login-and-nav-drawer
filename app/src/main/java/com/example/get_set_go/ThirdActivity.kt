package com.example.get_set_go

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.get_set_go.adapter.ItemAdapter
import com.example.get_set_go.data.Datasource
import com.example.get_set_go.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var binding : ActivityThirdBinding
    lateinit var recyclerView: RecyclerView
    private val LIST_VIEW = "LIST_VIEW"
    private val GRID_VIEW = "GRID_VIEW"
    var currentView = LIST_VIEW
    val myDataSet = Datasource().loadScroll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activationg recyclerView
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    private var isLinearLayoutManager = true

    private fun chooseLayout(){
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this,2)
        }
    }

    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null){
            return
        }
        menuItem.icon =
            if(isLinearLayoutManager){
                ContextCompat.getDrawable(this,R.drawable.ic_grid_layout)
            }else{
                ContextCompat.getDrawable(this,R.drawable.ic_linear_layout)
            }
        recyclerView.adapter = ItemAdapter(this,myDataSet)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.layout_menu,menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
package com.example.get_set_go

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class SecondActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        val uriSecondActivity = Uri.fromParts("package",packageName,null) // unable to use this in fragments
//        val uri = Uri.fromParts("package",packageName,null)
//        import methods/objects from activities to fragments using = "(activity as SecondActivity?)!!.packageName"

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true   // changes color when we click on "nav_menu item"
            when(it.itemId){
                R.id.nav_drawer_home -> {
                    Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
                    replaceFragment(HomepageFragment(),it.title.toString())
                }
                R.id.nav_drawer_cam -> {
                    Toast.makeText(applicationContext,"Clicked Get Started !", Toast.LENGTH_SHORT).show()
                    replaceFragment(CamFragment(),it.title.toString())
                }
                R.id.nav_drawer_top -> {
                    Toast.makeText(applicationContext,"Clicked Recommendations", Toast.LENGTH_SHORT).show()
                    replaceFragment(RecommendationsFragment(),it.title.toString())
                }
                R.id.nav_drawer_about-> Toast.makeText(applicationContext,"Clicked View", Toast.LENGTH_SHORT).show()
                R.id.nav_drawer_logout -> Toast.makeText(applicationContext,"Clicked Login", Toast.LENGTH_SHORT).show()
                R.id.nav_drawer_share -> Toast.makeText(applicationContext,"Clicked Rate Us", Toast.LENGTH_SHORT).show()
                R.id.nav_drawer_rate -> Toast.makeText(applicationContext,"Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_drawer_contact -> Toast.makeText(applicationContext,"Clicked Sync", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment, title: String){
        val fragmentManager = supportFragmentManager
        val fragmentTrnsaction = fragmentManager.beginTransaction()
        fragmentTrnsaction.replace(R.id.frame_layout,fragment)
        fragmentTrnsaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }
}
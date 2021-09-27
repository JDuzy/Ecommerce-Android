package org.bedu.proyectobedu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.bedu.proyectobedu.home.HomeFragment

const val BEDU_URL = "https://www.bedu.org"

class MainActivity : AppCompatActivity() {
    lateinit var bottom: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBar = findViewById<Toolbar>(R.id.appBar)
        this.setSupportActionBar(appBar)

        setUpBottomNavigation()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemSearch -> Toast.makeText(this, "La funcionalidad de búsqueda esta siendo implementada", Toast.LENGTH_SHORT).show()
            R.id.itemHelp -> {
                val browse = Intent( Intent.ACTION_VIEW , Uri.parse( BEDU_URL ) )
                startActivity( browse )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpBottomNavigation() {
        bottom = findViewById(R.id.navigation)
        bottom.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.action_home -> transaction.replace(R.id.my_nav_host_fragment, HomeFragment(), "Home fragment")
                R.id.action_cart -> transaction.replace(R.id.my_nav_host_fragment, CartFragment(), "Cart fragment")
                R.id.action_profile -> transaction.replace(R.id.my_nav_host_fragment, ProfileFragment(), "Profile fragment")
            }
            transaction.commit()
            return@setOnItemSelectedListener true
        }
    }
}
package org.bedu.proyectobedu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewStub
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

const val BEDU_URL = "https://www.bedu.org"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set ups custom appBar
        this.setSupportActionBar(findViewById(R.id.appBar))

        //Set ups BottomNav with NavController, when BottomNav is inflated
        val navController = findNavController(R.id.my_nav_host_fragment)
        val bottomNavStub = findViewById<ViewStub>(R.id.navigation)
        bottomNavStub.setOnInflateListener(ViewStub.OnInflateListener { viewStub, view ->
            findViewById<BottomNavigationView>(R.id.navigation)?.setupWithNavController(navController)
        })

        lifecycleScope.launchWhenResumed {
            findNavController(R.id.my_nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
                Log.d("destination", destination.toString())
                val bottomNav : View = findViewById(R.id.navigation)
                when (destination.id) {
                    R.id.productDetailsFragment -> bottomNav.visibility = GONE
                    R.id.loginFragment -> bottomNav.visibility = GONE
                    R.id.registrationFragment -> bottomNav.visibility = GONE
                    else -> bottomNav.visibility = VISIBLE
                }
            }
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemSearch -> Toast.makeText(this, "La funcionalidad de búsqueda esta siendo implementada", Toast.LENGTH_SHORT).show()
            R.id.itemHelp -> {
                //Navigates to the specified URL on main browser
                val browse = Intent( Intent.ACTION_VIEW , Uri.parse( BEDU_URL ) )
                startActivity( browse )
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
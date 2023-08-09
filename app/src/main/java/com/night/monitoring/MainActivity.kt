package com.night.monitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.night.monitoring.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        navController = findNavController(R.id.nav_fragment)


        val actionBar = supportActionBar


        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 0F

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//
//                R.id.loginFragment-> {
//                    actionBar!!.show()
//
//
//                }
//
//                else -> {
//                    actionBar!!.hide()
//
//                }
//            }
//        }
//
//    }
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            when (item.itemId) {
//                android.R.id.home -> {
//                    onBackPressedDispatcher.onBackPressed()
//                }
//            }
//            return super.onContextItemSelected(item)
//        }
//
//        override fun onBackPressed() {
//            var backState = false
//            navController.addOnDestinationChangedListener { _, destination, _ ->
//                when (destination.id) {
//                    R.id.splashFragment,
//                    R.id.loginFragment,
//                    R.id.bayarFragment,
//                    R.id.menuFragment -> {
//                        backState = false
//                    }
//                    else -> {
//                        backState = true
//                    }
//                }
//            }
//            if (backState) {
//                onBackPressedDispatcher.onBackPressed()
//            } else {
//                Toast.makeText(this, "asda", Toast.LENGTH_SHORT).show()
//            }
////        }
    }
}



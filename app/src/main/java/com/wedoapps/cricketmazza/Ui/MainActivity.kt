package com.wedoapps.cricketmazza.Ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.wedoapps.cricketmazza.R
import com.wedoapps.cricketmazza.Repository.CricketMazzaRepository
import com.wedoapps.cricketmazza.Utils.ViewModelProviderFactory
import com.wedoapps.cricketmazza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CricketMazzaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.customToolbar.toolbarTop
        setSupportActionBar(toolbar)

        val bacKBtn = binding.customToolbar.btnBack

        binding.customToolbar.btnShare.setOnClickListener {
            Snackbar.make(binding.root, "Shared Clicked", Snackbar.LENGTH_SHORT).show()
        }

        val repository = CricketMazzaRepository()
        val viewModelProvider = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProvider)[CricketMazzaViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val bottomNavigationView = binding.bottomNav
        val chipNavigationBar = binding.bottomBar
        chipNavigationBar.setItemSelected(R.id.home, true)
        chipNavigationBar.setOnItemSelectedListener { itemId ->
            bottomNavigationView.selectedItemId = itemId
        }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bacKBtn.setOnClickListener {
            navController.popBackStack()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home || destination.id == R.id.update || destination.id == R.id.live || destination.id == R.id.recent || destination.id == R.id.upcoming) {
                bacKBtn.visibility = View.INVISIBLE
                chipNavigationBar.visibility = View.VISIBLE
            } else {
                chipNavigationBar.visibility = View.GONE
                bacKBtn.visibility = View.VISIBLE
            }
        }
    }
}
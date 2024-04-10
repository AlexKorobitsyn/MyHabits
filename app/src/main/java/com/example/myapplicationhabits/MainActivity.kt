package com.example.myapplicationhabits

import RedactHabitFragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.myapplicationhabits.databinding.ActivityMainBinding
import com.example.myapplicationhabits.databinding.FragmentFirstFragMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FirstFragMain.OnFabClickedListener{
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout = binding.drawerLayout
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
        val fragmentManager: FragmentManager = supportFragmentManager
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForPagerFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onResume() {
        super.onResume()
    }
    private fun openFrag(f:Fragment, idHolder: Int)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .addToBackStack(null)
            .commit()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForPagerFragment()).commit()
            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InfoFragment()).commit()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onFabClicked() {
        openFrag(RedactHabitFragment(), R.id.fragment_container)
    }



}
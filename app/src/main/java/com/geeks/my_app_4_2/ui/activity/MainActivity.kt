package com.geeks.my_app_4_2.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.databinding.ActivityMainBinding
import com.geeks.my_app_4_2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = null


        val preferenceHelper = PreferenceHelper(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


        if (preferenceHelper.isOnboardShown && preferenceHelper.isSignupShown) {
            navController.navigate(R.id.noteFragment)
        } else if (!preferenceHelper.isSignupShown && preferenceHelper.isOnboardShown) {
            navController.navigate(R.id.signUpFragment)
        } else {
            navController.navigate(R.id.onboardFragment)
        }


        val navView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.note_nav_menu -> {
                    navController.navigate(R.id.noteFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.chat_nav_menu -> {
                    navController.navigate(R.id.chatFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.onboard_nav -> {
                    navController.navigate(R.id.onboardFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.signup_nav -> {
                    navController.navigate(R.id.signUpFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.detail_nav -> {
                    navController.navigate(R.id.detailFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
        }
    }

    fun openDrawer() {
        if (::binding.isInitialized) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}
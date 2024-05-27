package com.geeks.my_app_4_2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.databinding.ActivityMainBinding
import com.geeks.my_app_4_2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var preferenceHelper = PreferenceHelper(this)

        val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        if (preferenceHelper.isOnboardShown && preferenceHelper.isSignupShown) {
            navController.navigate(R.id.noteFragment)
        } else if (!preferenceHelper.isSignupShown && preferenceHelper.isOnboardShown) {
            navController.navigate(R.id.signUpFragment)
        } else {
            navController.navigate(R.id.onboardFragment)
        }
    }
}





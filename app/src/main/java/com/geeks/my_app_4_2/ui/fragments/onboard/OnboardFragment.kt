package com.geeks.my_app_4_2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.databinding.FragmentOnboardBinding
import com.geeks.my_app_4_2.ui.adapter.OnBoardViewPagerAdapter
import com.geeks.my_app_4_2.utils.PreferenceHelper
import com.google.android.material.tabs.TabLayoutMediator


class OnboardFragment : Fragment() {

    private lateinit var binding: FragmentOnboardBinding
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnboardBinding.inflate(inflater, container, false)
        preferenceHelper = PreferenceHelper(requireContext())
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }
    private fun initialize() {
        val tabLayout = binding.intoTabLayout
        val viewPager2 = binding.viewPager2
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position -> }.attach()
    }
    private fun setupListener() = with(binding.viewPager2) {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.skipText.visibility = View.INVISIBLE
                    if(position == 2){
                        binding.initTv.visibility = View.VISIBLE
                    }
                } else {
                    binding.skipText.visibility = View.VISIBLE
                    binding.initTv.visibility = View.INVISIBLE
                }
            }
        })
        binding.skipText.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2)
            }
        }
        binding.initTv.setOnClickListener{
            preferenceHelper.isOnboardShown = true
            findNavController().navigate(R.id.action_onboardFragment_to_noteFragment)
        }
    }
}



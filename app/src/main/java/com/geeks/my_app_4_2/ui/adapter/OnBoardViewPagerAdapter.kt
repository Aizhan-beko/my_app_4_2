package com.geeks.my_app_4_2.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geeks.my_app_4_2.ui.fragments.onboard.OnboardViewPagerFragment

class OnBoardViewPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) = OnboardViewPagerFragment().apply{
        arguments = Bundle().apply{
            putInt(OnboardViewPagerFragment.ARG_ONBOARD_POSITION, position)

        }
    }
}

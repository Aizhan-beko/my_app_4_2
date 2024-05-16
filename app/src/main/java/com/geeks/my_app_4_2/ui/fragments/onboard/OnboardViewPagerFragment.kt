package com.geeks.my_app_4_2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.databinding.FragmentOnboardViewPagerBinding


class OnboardViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnboardViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                onTxt.text = "Очень удобный функционал"
                lottieView.setAnimation(R.raw.first)
                lottieView.playAnimation()
            }
            1 -> {
                onTxt.text = "Быстрый, качественный продукт"
                lottieView.setAnimation(R.raw.second)
                lottieView.playAnimation()
            }
            2 -> {
                onTxt.text = "Куча функций и интересных фишек"
                lottieView.setAnimation(R.raw.third)
                lottieView.playAnimation()
            }
        }
    }
    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}

package com.geeks.my_app_4_2.ui.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.btnAddText.setOnClickListener {
            val et = binding.etAdd.text.toString()
            setBackStackData("key", et, true)
        }
    }

    private fun setBackStackData(s: String, et: String, b: Boolean) {

    }
}


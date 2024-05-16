package com.geeks.my_app_4_2.ui.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geeks.my_app_4_2.databinding.FragmentNoteBinding
import com.geeks.my_app_4_2.utils.PreferenceHelper


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root

            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setuplisteners()
    }

    private fun setuplisteners() = with(binding){

        val preferenceHelper = PreferenceHelper(requireContext())
        preferenceHelper.unit(requireContext())

        saveBtn.setOnClickListener{
            val et = edText.text.toString()
            preferenceHelper.title = et
            saveText.text = et
        }
        saveText.text = preferenceHelper.title

    }
}

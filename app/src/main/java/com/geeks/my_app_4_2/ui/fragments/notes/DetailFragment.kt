package com.geeks.my_app_4_2.ui.fragments.notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.geeks.my_app_4_2.App
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.data.models.NoteModel
import com.geeks.my_app_4_2.databinding.FragmentDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var selectedColor: String = "default_container"
    private var noteId: Int = -1

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
        setDateTime()
        setUpTextWatchers()
        setUpContainerClickListeners()
        update()
        setUpRadioGroupListener()
    }

    private fun update() {
        arguments?.let {
            noteId = it.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val args = App().getInstance()?.noteDao()?.getNoteById(noteId)
            args?.let { model ->
                binding.titleTv.setText(model.title)
                binding.descriptionTv.setText(model.description)

            }
        }
    }

    private fun setDateTime() {
        val currentTime = Calendar.getInstance().time
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormatter = SimpleDateFormat("MM-dd", Locale.getDefault())
        val formattedTime = timeFormatter.format(currentTime)
        val formattedDate = dateFormatter.format(currentTime)
        binding.tvTime.text = formattedTime
        binding.tvDate.text = formattedDate
    }

    private fun saveNoteToDatabase() {
        val title = binding.titleTv.text.toString()
        val description = binding.descriptionTv.text.toString()
        val currentTime = Calendar.getInstance().time
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormatter = SimpleDateFormat("MM-dd", Locale.getDefault())
        val formattedTime = timeFormatter.format(currentTime)
        val formattedDate = dateFormatter.format(currentTime)

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Title or description is empty", Toast.LENGTH_LONG)
                .show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val note = NoteModel(
                title = title,
                description = description,
                time = formattedTime,
                date = formattedDate,
                color = selectedColor
            )
            App().getInstance()?.noteDao()?.insertNote(note)
            lifecycleScope.launch(Dispatchers.Main) {

            }
        }
    }

    private fun setUpTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkReadyStatus()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.titleTv.addTextChangedListener(textWatcher)
        binding.descriptionTv.addTextChangedListener(textWatcher)
    }

    private fun setUpContainerClickListeners() {
        val containerClickListener = View.OnClickListener { view ->
            selectedColor = when (view.id) {
                R.id.dark_grey_con -> {
                    binding.darkGrey.isChecked = true
                    "dark_grey"
                }

                R.id.creamy_con -> {
                    binding.creamy.isChecked = true
                    "creamy"
                }

                R.id.dark_red_con -> {
                    binding.darkRed.isChecked = true
                    "dark_red"
                }

                else -> "default_container"
            }

        checkReadyStatus()
    }
        binding.darkGreyCon.setOnClickListener(containerClickListener)
        binding.creamyCon.setOnClickListener(containerClickListener)
        binding.darkRedCon.setOnClickListener(containerClickListener)
    }

    private fun setUpRadioGroupListener() {
        binding.radios.setOnCheckedChangeListener { group, checkedId ->
            selectedColor = when (checkedId) {
                R.id.dark_grey -> "dark_grey"
                R.id.creamy -> "creamy"
                R.id.dark_red -> "dark_red"
                else -> "default_container"
            }
            checkReadyStatus()
        }
    }
    private fun checkReadyStatus() {
        val isTitleFilled = binding.titleTv.text.toString().isNotEmpty()
        val isDescriptionFilled = binding.descriptionTv.text.toString().isNotEmpty()

        binding.readyTv.isEnabled = isTitleFilled && isDescriptionFilled
    }
        private fun setUpClickListener() {
            binding.readyTv.setOnClickListener {
                saveNoteToDatabase()
                findNavController().navigateUp()

            }
            binding.blackArrowImg.setOnClickListener{
                findNavController().navigate(R.id.action_detailFragment_to_noteFragment)
            }
    }
}

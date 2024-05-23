package com.geeks.my_app_4_2.ui.fragments.notes

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.my_app_4_2.App
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.data.models.NoteModel
import com.geeks.my_app_4_2.databinding.FragmentNoteBinding
import com.geeks.my_app_4_2.intefaces.OnClickItem
import com.geeks.my_app_4_2.ui.adapter.NoteAdapter


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private var isGridLayout = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root

            }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
        updateLayoutChangerIcon()
    }
    private fun initialize(){
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NoteAdapter(this@NoteFragment, this@NoteFragment)
        }
    }
    private fun setupListeners() = with(binding){
        addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_noteFragment_to_detailFragment)
        }
        layoutChanger.setOnClickListener {
            isGridLayout = !isGridLayout
            val layoutManager = if (isGridLayout) {
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())
            }
            binding.rvNote.layoutManager = layoutManager
        }
    }
    private fun updateLayoutChangerIcon() {
            val iconResId = if (isGridLayout) {
                R.drawable.linearlayout
            } else {
                R.drawable.grid
            }
            binding.layoutChanger.setImageResource(iconResId)
        }
    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner, Observer {
            (binding.rvNote.adapter as NoteAdapter).submitList(it)
            })
    }
    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder){
            setTitle("Are you sure,do you want to delete?")
            setPositiveButton("Yes"){dialog, which->
                App().getInstance()?.noteDao()?.deleteNote(noteModel)

            }
            setNegativeButton("No"){dialog, which->
                dialog.cancel()

            }
            show()
        }
        builder.create()
    }
    override fun onClick(noteModel: NoteModel) {
      val action = NoteFragmentDirections.actionNoteFragmentToDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}

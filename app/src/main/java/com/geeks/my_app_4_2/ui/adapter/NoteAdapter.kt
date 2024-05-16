package com.geeks.my_app_4_2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeks.my_app_4_2.data.models.NoteModel
import com.geeks.my_app_4_2.databinding.ItemNoteBinding


class NoteAdapter:androidx.recyclerview.widget.ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()){
    class ViewHolder (private val binding:ItemNoteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind (item:NoteModel){
            binding.itemText.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.title == newItem.title
        }

    }

}



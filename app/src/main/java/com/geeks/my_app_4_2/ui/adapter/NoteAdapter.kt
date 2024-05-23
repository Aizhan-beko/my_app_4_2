package com.geeks.my_app_4_2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeks.my_app_4_2.R
import com.geeks.my_app_4_2.data.models.NoteModel
import com.geeks.my_app_4_2.databinding.ItemNoteBinding
import com.geeks.my_app_4_2.intefaces.OnClickItem


class NoteAdapter( private val onLongClick: OnClickItem, private val onClick: OnClickItem):androidx.recyclerview.widget.ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()) {
    class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description
            binding.dateTv.text = item.date
            binding.timeTv.text = item.time
            binding.container.setBackgroundResource(getBackgroundResource(item.color))
        }
        private fun getBackgroundResource(color: String): Int {
            return when (color) {
                "dark_grey" -> R.drawable.dark_grey_container
                "creamy" -> R.drawable.creamy_container
                "dark_red" -> R.drawable.dark_red_container
                else -> R.drawable.default_container
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener{
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener{
            onClick.onClick(getItem(position))
        }
    }
    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

}



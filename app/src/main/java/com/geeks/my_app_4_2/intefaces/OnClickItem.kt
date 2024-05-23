package com.geeks.my_app_4_2.intefaces

import com.geeks.my_app_4_2.data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel:NoteModel)

    fun onClick(noteModel: NoteModel)
}
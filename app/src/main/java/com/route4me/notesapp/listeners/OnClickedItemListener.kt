package com.route4me.notesapp.listeners

import com.route4me.notesapp.db.NoteEntity

interface OnClickedItemListener {
    fun clickedNoteItem(note: NoteEntity)
}
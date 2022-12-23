package com.route4me.notesapp.repository

import com.route4me.notesapp.db.NoteDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val dao: NoteDao
) {

    suspend fun getAllNotes() = flow {

        emit(dao.getNoteList())

    }.flowOn(IO)

}
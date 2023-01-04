package com.route4me.notesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route4me.notesapp.db.NoteEntity
import com.route4me.notesapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject
constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val mutableNoteList = MutableLiveData<List<NoteEntity>>()
    val list: LiveData<List<NoteEntity>>
        get() = mutableNoteList

    fun getNotes() = viewModelScope.launch {

        repository.getAllNotes().collect { notes ->

            mutableNoteList.value = notes

        }

    }

    fun insetNote(note: NoteEntity) = viewModelScope.launch(IO) {

        repository.insertNote(note)
        getNotes()

    }

}
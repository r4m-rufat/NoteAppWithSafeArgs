package com.route4me.notesapp.ui.notes

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.route4me.notesapp.R
import com.route4me.notesapp.databinding.FragmentNotesBinding
import com.route4me.notesapp.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding
    get() = _binding as FragmentNotesBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var contex: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesBinding.inflate(layoutInflater)
        contex = requireContext()
        Log.d(TAG, "onCreateView: vies yene")
        setupAdapter()
        observeNotes()
        clickedAddButton()
        return binding.root
    }

    private fun observeNotes() {

        viewModel.getNotes()
        viewModel.list.observe(requireActivity()) { noteList ->

            noteAdapter.updateList(noteList)

        }

    }

    private fun setupAdapter() {

        noteAdapter = NoteAdapter(contex, emptyList())
        binding.recyclerNotes.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contex, LinearLayoutManager.VERTICAL, false)
            adapter = noteAdapter
        }

    }

    private fun clickedAddButton() {

        binding.icAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_notesFragment_to_noteDetailFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
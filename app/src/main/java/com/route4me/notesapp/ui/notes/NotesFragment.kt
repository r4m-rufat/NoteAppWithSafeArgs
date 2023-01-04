package com.route4me.notesapp.ui.notes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.route4me.notesapp.databinding.FragmentNotesBinding
import com.route4me.notesapp.db.NoteEntity
import com.route4me.notesapp.listeners.OnClickedItemListener
import com.route4me.notesapp.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(), OnClickedItemListener {

    private var _binding: FragmentNotesBinding? = null
    private val binding
    get() = _binding as FragmentNotesBinding
    private val viewModel by activityViewModels<MainActivityViewModel>()
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var contex: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesBinding.inflate(layoutInflater)
        contex = requireContext()
        setupAdapter()
        observeNotes()
        clickedAddButton()
        return binding.root
    }

    private fun observeNotes() {

        viewModel.getNotes()
        viewModel.list.observe(viewLifecycleOwner) { noteList ->

            noteAdapter.updateList(noteList)

        }

    }

    private fun setupAdapter() {

        noteAdapter = NoteAdapter(contex, emptyList(), this)
        binding.recyclerNotes.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contex, LinearLayoutManager.VERTICAL, false)
            adapter = noteAdapter
        }

    }

    private fun clickedAddButton() {

        binding.icAdd.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(null)
            findNavController().navigate(action)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clickedNoteItem(note: NoteEntity) {

        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(note)
        findNavController().navigate(action)

    }
}
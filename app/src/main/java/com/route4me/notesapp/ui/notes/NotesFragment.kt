package com.route4me.notesapp.ui.notes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.route4me.notesapp.R
import com.route4me.notesapp.databinding.FragmentNotesBinding
import com.route4me.notesapp.listeners.OnClickedItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(), OnClickedItemListener {

    private var _binding: FragmentNotesBinding? = null
    private val binding
    get() = _binding as FragmentNotesBinding
    private val viewModel by viewModels<NotesFragmentViewModel>()
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
        viewModel.list.observe(requireActivity()) { noteList ->

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
            Navigation.findNavController(it).navigate(R.id.action_notesFragment_to_noteDetailFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clickedNoteItem(id: Int) {

        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment()

    }
}
package com.route4me.notesapp.ui.note_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.route4me.notesapp.databinding.FragmentNoteDetailBinding
import com.route4me.notesapp.db.NoteDao
import com.route4me.notesapp.db.NoteEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null

    private val binding
        get() = _binding as FragmentNoteDetailBinding

    @Inject
    lateinit var dao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        clickedSaveButton()
        clickedIconBack()
        return binding.root
    }

    private fun clickedSaveButton() {

        binding.buttonSave.setOnClickListener {
            lifecycleScope.launch(IO) {

                dao.insertNote(
                    NoteEntity(
                        0,
                        binding.etxtTitle.text.trim().toString(),
                        binding.etxtDescription.text.trim().toString()
                    )
                )
                Navigation.findNavController(it).navigateUp()

            }
        }

    }

    private fun clickedIconBack() {

        binding.icBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
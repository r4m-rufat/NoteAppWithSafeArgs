package com.route4me.notesapp.ui.note_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.route4me.notesapp.databinding.FragmentNoteDetailBinding
import com.route4me.notesapp.db.NoteDao
import com.route4me.notesapp.db.NoteEntity
import com.route4me.notesapp.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null

    private val binding
        get() = _binding as FragmentNoteDetailBinding

    private val args by navArgs<NoteDetailFragmentArgs>()
    private val viewModel by activityViewModels<MainActivityViewModel>()

    @Inject
    lateinit var dao: NoteDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        clickedSaveButton()
        clickedIconBack()
        setNoteItemsIfNotNull()
        return binding.root
    }

    private fun clickedSaveButton() {

        binding.buttonSave.setOnClickListener {
            lifecycleScope.launch(IO) {

                viewModel.insetNote(
                    NoteEntity(
                        0,
                        binding.etxtTitle.text.trim().toString(),
                        binding.etxtDescription.text.trim().toString()
                    )
                )

            }
            findNavController().navigateUp()
        }

    }

    private fun setNoteItemsIfNotNull() {

        args.note?.let {
            binding.etxtTitle.setText(it.title)
            binding.etxtDescription.setText(it.description)
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
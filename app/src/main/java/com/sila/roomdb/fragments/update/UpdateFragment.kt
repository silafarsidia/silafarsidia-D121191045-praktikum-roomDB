package com.sila.roomdb.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sila.roomdb.R
import com.sila.roomdb.model.Note
import com.sila.roomdb.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.updateNoteTitle_et.setText(args.currentNote.noteTitle)
        view.updateNoteDesc_et.setText(args.currentNote.noteDesc)

        view.update_btn.setOnClickListener {

        }

        return view
    }

    private fun updateItem(){
        val noteTitle = updateNoteTitle_et.text.toString()
        val noteDesc = updateNoteDesc_et.text.toString()

        if(inputCheck(noteTitle, noteDesc)){
            // Create Note Object
            val updateNote = Note(args.currentNote.id, noteTitle, noteDesc)
            // Updated User
            mNoteViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Note Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate back to list fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out Empty Fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(noteTitle: String, noteDesc: String): Boolean{
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteDesc))
    }
}
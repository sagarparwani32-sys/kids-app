package com.kidsapp.notes.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kidsapp.notes.R
import com.kidsapp.notes.database.Note
import com.kidsapp.notes.databinding.ActivityNoteDetailsBinding
import com.kidsapp.notes.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

class NoteDetailsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityNoteDetailsBinding
    private lateinit var noteViewModel: NoteViewModel
    private var noteId: Long = -1
    private var isEditMode = false
    
    companion object {
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_CONTENT = "extra_note_content"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        
        noteId = intent.getLongExtra(EXTRA_NOTE_ID, -1)
        isEditMode = noteId != -1L
        
        if (isEditMode) {
            title = "Edit Note"
            binding.editTextTitle.setText(intent.getStringExtra(EXTRA_NOTE_TITLE))
            binding.editTextContent.setText(intent.getStringExtra(EXTRA_NOTE_CONTENT))
        } else {
            title = "Add Note"
        }
        
        binding.buttonSave.setOnClickListener {
            saveNote()
        }
    }
    
    private fun saveNote() {
        val title = binding.editTextTitle.text.toString().trim()
        val content = binding.editTextContent.text.toString().trim()
        
        if (title.isEmpty()) {
            binding.editTextTitle.error = "Title is required"
            binding.editTextTitle.requestFocus()
            return
        }
        
        if (content.isEmpty()) {
            binding.editTextContent.error = "Content is required"
            binding.editTextContent.requestFocus()
            return
        }
        
        if (isEditMode) {
            val updatedNote = Note(
                id = noteId,
                title = title,
                content = content,
                timestamp = System.currentTimeMillis()
            )
            noteViewModel.update(updatedNote)
            Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            val newNote = Note(
                title = title,
                content = content
            )
            noteViewModel.insert(newNote)
            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show()
        }
        
        finish()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

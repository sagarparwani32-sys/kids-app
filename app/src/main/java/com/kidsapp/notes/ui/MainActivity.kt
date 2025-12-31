package com.kidsapp.notes.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kidsapp.notes.R
import com.kidsapp.notes.database.Note
import com.kidsapp.notes.databinding.ActivityMainBinding
import com.kidsapp.notes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var notesAdapter: NotesAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        
        setupRecyclerView()
        observeNotes()
        
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(
            onNoteClick = { note ->
                val intent = Intent(this, NoteDetailsActivity::class.java).apply {
                    putExtra(NoteDetailsActivity.EXTRA_NOTE_ID, note.id)
                    putExtra(NoteDetailsActivity.EXTRA_NOTE_TITLE, note.title)
                    putExtra(NoteDetailsActivity.EXTRA_NOTE_CONTENT, note.content)
                }
                startActivity(intent)
            },
            onDeleteClick = { note ->
                showDeleteConfirmationDialog(note)
            }
        )
        
        binding.recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = notesAdapter
        }
    }
    
    private fun observeNotes() {
        noteViewModel.allNotes.observe(this) { notes ->
            if (notes.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
                binding.recyclerViewNotes.visibility = View.GONE
            } else {
                binding.emptyView.visibility = View.GONE
                binding.recyclerViewNotes.visibility = View.VISIBLE
                notesAdapter.submitList(notes)
            }
        }
    }
    
    private fun showDeleteConfirmationDialog(note: Note) {
        AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete \"${note.title}\"?")
            .setPositiveButton("Delete") { _, _ ->
                noteViewModel.delete(note)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}

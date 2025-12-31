package com.kidsapp.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kidsapp.notes.database.Note
import com.kidsapp.notes.database.NoteDatabase
import com.kidsapp.notes.database.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>
    
    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }
    
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
    
    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }
    
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
    
    fun deleteById(noteId: Long) = viewModelScope.launch {
        repository.deleteById(noteId)
    }
    
    suspend fun getNoteById(noteId: Long): Note? {
        return repository.getNoteById(noteId)
    }
}

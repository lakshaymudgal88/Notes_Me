package com.mudgal.notesme.feature_notes.domain.use_cases

import com.mudgal.notesme.feature_notes.domain.model.InvalidNoteException
import com.mudgal.notesme.feature_notes.domain.model.Note
import com.mudgal.notesme.feature_notes.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Note title should not be empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Note content should not be empty!")
        }
        repository.insertNote(note)
    }
}
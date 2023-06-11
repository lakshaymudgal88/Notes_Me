package com.mudgal.notesme.feature_notes.domain.use_cases

import com.mudgal.notesme.feature_notes.domain.model.Note
import com.mudgal.notesme.feature_notes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
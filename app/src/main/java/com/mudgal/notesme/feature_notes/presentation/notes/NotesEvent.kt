package com.mudgal.notesme.feature_notes.presentation.notes

import com.mudgal.notesme.feature_notes.domain.model.Note
import com.mudgal.notesme.feature_notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}

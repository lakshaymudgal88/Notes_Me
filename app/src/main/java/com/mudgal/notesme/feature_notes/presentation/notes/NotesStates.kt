package com.mudgal.notesme.feature_notes.presentation.notes

import com.mudgal.notesme.feature_notes.domain.model.Note
import com.mudgal.notesme.feature_notes.domain.util.NoteOrder
import com.mudgal.notesme.feature_notes.domain.util.OrderType

data class NotesStates(
    val notes: List<Note> = emptyList(),
    val notesOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

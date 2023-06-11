package com.mudgal.notesme.feature_notes.presentation.add_edit_note

sealed class UiState {
    data class ShowSnackBar(val message: String) : UiState()
    object SaveNote : UiState()
}

package com.mudgal.notesme.feature_notes.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteUIState {
    data class EnteredTitle(val value: String) : AddEditNoteUIState()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteUIState()
    data class EnteredContent(val value: String) : AddEditNoteUIState()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteUIState()
    data class ChangeColor(val color: Int) : AddEditNoteUIState()
    object SaveNote : AddEditNoteUIState()
}

package com.mudgal.notesme.feature_notes.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudgal.notesme.feature_notes.domain.model.InvalidNoteException
import com.mudgal.notesme.feature_notes.domain.model.Note
import com.mudgal.notesme.feature_notes.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentNoteId: Int? = null

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter Title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "enter content..."
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventState = MutableSharedFlow<UiState>()
    val eventState = _eventState.asSharedFlow()

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.also {
                        _noteTitle.value = noteTitle.value.copy(
                            title = it.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            title = it.content,
                            isHintVisible = false
                        )
                        _noteColor.value = it.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteUIState) {
        when (event) {
            is AddEditNoteUIState.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    title = event.value
                )
            }
            is AddEditNoteUIState.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.title.isBlank()
                )
            }
            is AddEditNoteUIState.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    title = event.value
                )
            }
            is AddEditNoteUIState.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.title.isBlank()
                )
            }
            is AddEditNoteUIState.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteUIState.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = noteTitle.value.title,
                                content = noteContent.value.title,
                                color = noteColor.value,
                                timeStamp = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventState.emit(
                            UiState.SaveNote
                        )
                    } catch (e: InvalidNoteException) {
                        _eventState.emit(
                            UiState.ShowSnackBar(
                                e.message ?: "couldn't save note!"
                            )
                        )
                    }
                }
            }
        }
    }
}
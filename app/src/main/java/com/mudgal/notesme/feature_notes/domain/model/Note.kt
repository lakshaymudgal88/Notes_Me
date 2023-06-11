package com.mudgal.notesme.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mudgal.notesme.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val color: Int,
    val timeStamp: Long
) {
    companion object {
        val noteColors = listOf(
            RedOrange, LightGreen, Violet, BabyBlue, RedPink
        )
    }
}

class InvalidNoteException(message: String) : Exception(message)
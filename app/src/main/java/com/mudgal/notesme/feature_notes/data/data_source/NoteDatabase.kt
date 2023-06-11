package com.mudgal.notesme.feature_notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mudgal.notesme.feature_notes.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract val roomDao: NoteDao

    companion object {
        const val DB_NAME = "notes_db"
    }
}
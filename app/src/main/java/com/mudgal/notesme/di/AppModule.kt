package com.mudgal.notesme.di

import android.app.Application
import androidx.room.Room
import com.mudgal.notesme.feature_notes.data.data_source.NoteDatabase
import com.mudgal.notesme.feature_notes.data.repository.NoteRepositoryImpl
import com.mudgal.notesme.feature_notes.domain.repository.NoteRepository
import com.mudgal.notesme.feature_notes.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(database.roomDao)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotesUseCase(repository),
            DeleteNoteUseCase(repository),
            AddNoteUseCase(repository),
            GetNoteUseCase(repository)
        )
    }
}
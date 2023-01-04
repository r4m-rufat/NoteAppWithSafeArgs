package com.route4me.notesapp.di

import android.content.Context
import androidx.room.Room
import com.route4me.notesapp.db.NoteDao
import com.route4me.notesapp.db.NoteDatabase
import com.route4me.notesapp.repository.MainRepository
import com.route4me.notesapp.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getDao(@ApplicationContext context: Context): NoteDao = Room.databaseBuilder(
        context, NoteDatabase::class.java, DB_NAME
    ).build().dao()

    @Provides
    fun provideNotesRepository(dao: NoteDao) = MainRepository(dao)

}
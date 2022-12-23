package com.route4me.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun dao(): NoteDao

}

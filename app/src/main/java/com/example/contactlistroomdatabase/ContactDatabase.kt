package com.example.contactlistroomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 2
)
abstract class ContactDatabase : RoomDatabase() {
    abstract  val dao:ContactDao

}
package com.example.contactlistroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {

    @Upsert //insert and update both
   suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query(" SELECT * FROM  contact ORDER BY firstName ASC ")
    fun getContactsOrderByFirstName():Flow<List<Contact>>

    @Query(" SELECT * FROM  contact ORDER BY lastName ASC ")
    fun getContactsOrderByLastName():Flow<List<Contact>>
    @Query(" SELECT * FROM  contact ORDER BY phoneNumber ASC ")
    fun getContactsOrderByPhoneNumber():Flow<List<Contact>>



}
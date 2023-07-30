package com.example.contactlistroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String = "",
    val bloodGroup: String = "",
    val department: String = "",
    val homeDistrict: String = "",
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

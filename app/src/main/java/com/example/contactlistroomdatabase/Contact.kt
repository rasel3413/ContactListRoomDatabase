package com.example.contactlistroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Contact(
    var firstName:String,
    var lastName:String,
    var phoneNumber:String,
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
)

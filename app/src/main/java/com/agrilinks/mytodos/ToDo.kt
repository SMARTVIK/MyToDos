package com.agrilinks.mytodos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(@PrimaryKey val _id: String, val title : String, val description : String, var checked : Boolean)
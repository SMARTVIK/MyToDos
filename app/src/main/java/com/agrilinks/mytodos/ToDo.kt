package com.agrilinks.mytodos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ToDo(@PrimaryKey val _id: String, val title : String, val description : String, var checked : Boolean) : Parcelable
package com.agrilinks.mytodos

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun daoAccess(): ToDoDao?

    companion object {
        var INSTANCE: ToDoDatabase? = null

        fun getAppDataBase(context: Context): ToDoDatabase? {
            if (INSTANCE == null) {
                synchronized(ToDoDatabase::class) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
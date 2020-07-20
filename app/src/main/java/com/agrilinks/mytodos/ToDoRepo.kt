package com.agrilinks.mytodos

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room

class ToDoRepo(var context : Context) {

    private val DB_NAME: String = "TODO_DB"
    var database : ToDoDatabase ? = null
    init {
        database = Room.databaseBuilder<ToDoDatabase>(
            context,
            ToDoDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }


    fun insertTask(note: ToDo) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                database!!.daoAccess()?.insertToDo(note)
                return null
            }
        }.execute()
    }

    fun getToDos(): LiveData<List<ToDo>> {
        return database!!.daoAccess()?.fetchAllToDos()!!
    }

}
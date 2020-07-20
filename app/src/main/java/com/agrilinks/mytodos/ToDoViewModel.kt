package com.agrilinks.mytodos

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel(var repo: ToDoRepo) : ViewModel() {

    var toDoList : LiveData<List<ToDo>> ? = null

    init {
        toDoList = repo.getToDos()
    }

    fun insert(toDo : ToDo) {
        repo.insertTask(toDo)
    }
}
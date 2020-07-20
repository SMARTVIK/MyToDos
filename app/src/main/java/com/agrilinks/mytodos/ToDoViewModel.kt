package com.agrilinks.mytodos

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel(var repo: ToDoRepo) : ViewModel() {

    fun getToDos() {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val value : List<ToDo> = repo.getToDos()
                toDoList.postValue(value)
                return null
            }
        }.execute()
    }

    var toDoList : MutableLiveData<List<ToDo>> = MutableLiveData()
}
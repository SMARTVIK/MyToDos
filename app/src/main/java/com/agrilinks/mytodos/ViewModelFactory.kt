package com.agrilinks.mytodos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(var context : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var repo = ToDoRepo(context)
        return modelClass.getConstructor(ToDoRepo::class.java).newInstance(repo)
    }
}

package com.agrilinks.mytodos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDo(todo : ToDo)

    @Query("SELECT * FROM ToDo")
    fun fetchAllToDos(): List<ToDo>

    @Update
    fun updateToDo(todo : ToDo)

    @Delete
    fun deleteToDo(todo : ToDo)

}

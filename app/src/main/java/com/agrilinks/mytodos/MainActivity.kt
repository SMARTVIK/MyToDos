package com.agrilinks.mytodos

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_item.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_ITEM: Int = 100
    private lateinit var toDoAdapter: ToDoApdater
    private var context : Context? = null
    private var viewModel : ToDoViewModel ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelFacotry = ViewModelFactory(applicationContext!!)
        context = MainActivity@this
        setUpToDoList()
        viewModel = ViewModelProviders.of(this,viewModelFacotry).get(ToDoViewModel::class.java)
        viewModel!!.toDoList.observe(this, Observer {
        toDoAdapter.setData(it)
        })
        viewModel!!.getToDos()
    }

    private fun setUpToDoList() {
        var layoutManager = LinearLayoutManager(MainActivity@this)
        layoutManager.orientation = RecyclerView.VERTICAL
        todo_list.layoutManager = layoutManager
        toDoAdapter = ToDoApdater(object : OnAddItemClick{
            override fun onAddClick() {
                var intent = Intent(context,AddItem::class.java)
                startActivityForResult(intent,ADD_ITEM)
            }
        })
        todo_list.adapter = toDoAdapter
        todo_list.addItemDecoration(DividerItemDecoration(MainActivity@this,RecyclerView.VERTICAL))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_ITEM) {
            if(resultCode == Activity.RESULT_OK) {
                viewModel!!.getToDos()
            }
        }
    }
}
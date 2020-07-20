package com.agrilinks.mytodos

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoApdater(var onAddItemClick: OnAddItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ADD: Int = 1
    private val NORMAL: Int = 2
    var todoList: MutableList<ToDo> = mutableListOf<ToDo>()

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_title : TextView ? = null
        var tv_description : TextView ? = null
        var checkBox : CheckBox ? = null

        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_description = itemView.findViewById(R.id.tv_description)
            checkBox = itemView.findViewById(R.id.checkbox)
            checkBox!!.isEnabled = false

           itemView.setOnClickListener(object : View.OnClickListener{
               override fun onClick(v: View?) {
                   var toDo = todoList.get(layoutPosition - 1)
                   if (!toDo.checked) {
                       val initPos = todoList.indexOf(toDo)
                       todoList!!.remove(toDo)
                       todoList.add(toDo)
                       toDo.checked = true
                       val lastPosition = todoList.indexOf(toDo)
                       notifyItemMoved(initPos, lastPosition)
                       notifyDataSetChanged()
                   }
               }
           })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ADD) {
            return AddItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.todo_item_add, parent, false)
            )
        } else {
            return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
            )
        }
    }

    inner class AddItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var addView: TextView? = null

        init {
            addView = itemView?.findViewById(R.id.tv_add_view)
            itemView?.setOnClickListener {
                onAddItemClick.onAddClick()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ADD else NORMAL
    }

    override fun getItemCount(): Int {
       return todoList!!.size + 1
    }

    override fun onBindViewHolder(holder1: RecyclerView.ViewHolder, position: Int) {

        if (holder1 is ItemViewHolder && position != 0) {
            var holder = holder1 as ItemViewHolder
            val todoModel = todoList.get(position-1)
            holder.tv_title?.setText(todoModel.title)
            holder.tv_description?.setText(todoModel.description)
            if (todoModel.checked) {
                holder.itemView.setBackgroundColor(Color.GRAY)
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
            holder.checkBox?.isChecked = todoModel.checked
        }

    }

    fun setData(it: List<ToDo>?) {
        this.todoList = it!!.toMutableList()
        notifyDataSetChanged()
    }

}

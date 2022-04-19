package com.test.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        if (!todos.contains(todo)) {
            todos.add(todo)
            notifyItemInserted(todos.size - 1)
        }
    }

    private fun deleteTodo(todo: Todo) {
        val position: Int = todos.indexOfFirst { t -> t.title == todo.title }
        todos.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun toggleTextThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = currTodo.title
            checkBoxTodo.isChecked = currTodo.isChecked
            toggleTextThrough(tvTodoTitle, currTodo.isChecked)
            checkBoxTodo.setOnCheckedChangeListener { _, isChecked ->
                toggleTextThrough(tvTodoTitle, isChecked)
                currTodo.isChecked = !isChecked
            }
            /* Delete Button */
            imageButton2Todo.setOnClickListener { _ ->
                deleteTodo(currTodo)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
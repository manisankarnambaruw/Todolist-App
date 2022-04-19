package com.test.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* RecyclerView Layout */
        todoAdapter = TodoAdapter(mutableListOf())

        rvLayout.adapter = todoAdapter
        rvLayout.layoutManager = LinearLayoutManager(this)

        imageButton.setOnClickListener { _ ->
            val todoTitle: String = textInput.text.toString().trim()
            if (todoTitle.isNotEmpty() or todoTitle.isNotBlank()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                textInput.text?.clear()
            }
        }
    }
}
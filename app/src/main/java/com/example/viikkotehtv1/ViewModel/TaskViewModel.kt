package com.example.viikkotehtv1.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikkotehtv1.domain.Task
import kotlin.collections.plus

class TaskViewModel : ViewModel()
{
    var tasks by mutableStateOf(listOf<Task>())

    init {
        tasks = listOf(
            Task(1, "Osta maitoa", "Muista laktoositon", 1, "2026-01-15", false),
            Task(2, "Tee viikkotehtävä", "Viikko 1", 2, "2026-01-16", false),
            Task(3, "Lenkille", "5 km", 3, "2026-02-15", true),
            Task(4, "Osta mehua", "Omehamehu", 1, "2026-02-16", false),
            Task(5, "Osta mehua", "Appelsiinimehu", 1, "2026-01-25", false),
        )
    }

    fun addTask(task: Task)
    {
        tasks = tasks + task
    }

    fun toggleDone(id: Int)
    {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int)
    {
        tasks = tasks.filterNot { it.id == id }
    }

    fun filterByDone(done: Boolean): List<Task>
    {
        return tasks.filter { it.done == done }
    }

    fun sortByDueDate()
    {
        tasks = tasks.sortedBy { it.dueDate }
    }
}
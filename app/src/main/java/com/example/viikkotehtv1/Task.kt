package com.example.viikkotehtv1

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)

fun addTask(list: MutableList<Task>, task: Task) {
    list.add(task)
}

fun toggleDone(list: MutableList<Task>, id: Int) {
    val i = list.indexOfFirst { it.id == id }
    if (i != -1) {
        list[i] = list[i].copy(done = !list[i].done)
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}
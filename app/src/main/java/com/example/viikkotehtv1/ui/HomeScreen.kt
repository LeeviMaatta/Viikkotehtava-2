package com.example.viikkotehtv1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.example.viikkotehtv1.ViewModel.TaskViewModel
import com.example.viikkotehtv1.domain.Task

@Composable
fun HomeScreen(viewModel: TaskViewModel)
{

    var showOnlyDone by remember { mutableStateOf(false) }

    val visibleTasks =
        if (showOnlyDone) viewModel.filterByDone(true)
        else viewModel.tasks

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text("Tehtävälista")

        Button(onClick = { showOnlyDone = !showOnlyDone }) {
            Text(if (showOnlyDone) "Näytä kaikki" else "Näytä vain valmiit")
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(visibleTasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )

                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f)
                    )

                    Button(onClick = { viewModel.removeTask(task.id) }) {
                        Text("Poista")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.sortByDueDate() }) {
            Text("Järjestä deadlinen mukaan")
        }

        Spacer(modifier = Modifier.height(12.dp))

        AddTaskForm(viewModel)
    }
}

@Composable
fun AddTaskForm(viewModel: TaskViewModel)
{
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    Column {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Tehtävän nimi") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Kuvaus") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Deadline (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    viewModel.addTask(
                        Task(
                            id = viewModel.tasks.size + 1,
                            title = title,
                            description = description,
                            priority = 1,
                            dueDate = dueDate,
                            done = false
                        )
                    )
                    title = ""
                    description = ""
                    dueDate = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lisää tehtävä")
        }
    }
}
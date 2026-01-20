package com.example.viikkotehtv1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viikkotehtv1.ui.theme.Viikkotehtävä1Theme
import com.example.viikkotehtv1.Task

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtävä1Theme {
                val tasks = remember {
                    mutableStateListOf(
                        Task(1, "Osta maitoa", "Muista laktoositon", 1, "15-2-2026", false),
                        Task(2, "Tee viikkotehtävä", "Viikko 1", 2, "21-1-2026", false),
                        Task(3, "Lenkille", "5 km", 3, "15-1-2026", true),
                        Task(4, "Osta mehua", "Omehamehu", 1, "15-2-2026", false),
                        Task(5, "Osta mehua", "Appelsiinimehu", 1, "16-2-2026", false),
                    )
                }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(tasks)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(tasks: MutableList<Task>)
{
    var showOnlyDone by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(8.dp))

    val visibleTasks = if (showOnlyDone) {
        filterByDone(tasks, true)
    } else {
        tasks
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(text = "Tehtävälista")

        FilterByDoneButton(
            showOnlyDone = showOnlyDone,
            onToggle = { showOnlyDone = !showOnlyDone }
        )

        visibleTasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = task.done,
                    onCheckedChange = {
                        toggleDone(tasks, task.id)
                    }
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = task.title
                    )
                    Text(
                        text = task.description
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        SortByDueDateButton(tasks)

        Spacer(modifier = Modifier.height(8.dp))
        AddTaskForm(tasks)
    }
}

@Composable
fun SortByDueDateButton(tasks: MutableList<Task>)
{
    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = {
        val sorted = sortByDueDate(tasks)
        tasks.clear()
        tasks.addAll(sorted)
    }) {
        Text("Järjestä deadlinen mukaan")
    }
}

@Composable
fun FilterByDoneButton(
    showOnlyDone: Boolean,
    onToggle: () -> Unit
) {
    Button(onClick = onToggle) {
        Text(
            if (showOnlyDone) "Näytä kaikki"
            else "Näytä vain valmiit"
        )
    }
}

@Composable
fun AddTaskForm(tasks: MutableList<Task>)
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

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Kuvaus") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Deadline") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    addTask(
                        tasks,
                        Task(
                            id = tasks.size + 1,
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview()
{
    val previewTasks = remember {
        mutableStateListOf(
            Task(1, "Osta maitoa", "Muista laktoositon", 1, "15-2-2026", false),
            Task(2, "Tee viikkotehtävä", "Viikko 1", 2, "21-1-2026", false),
            Task(3, "Lenkille", "5 km", 3, "15-1-2026", true)
        )
    }

    Viikkotehtävä1Theme {
        HomeScreen(previewTasks)
    }
}
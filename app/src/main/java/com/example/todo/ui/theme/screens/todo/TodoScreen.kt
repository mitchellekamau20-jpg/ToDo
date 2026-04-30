package com.example.todo.ui.theme.screens.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel = viewModel()
) {
    val activity by todoViewModel.activity.collectAsState()
    
    var activityTitle by remember { mutableStateOf("") }
    var activityDescription by remember { mutableStateOf("") }
    var activityMedia by remember { mutableStateOf("") }
    var isComplete by remember { mutableStateOf(value = false) }
    var activityDuedate by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Create New Activity",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = activityTitle,
            onValueChange = { activityTitle = it },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        
        OutlinedTextField(
            value = activityDescription,
            onValueChange = { activityDescription = it },
            label = { Text(text = "Activity Description") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
        
        OutlinedTextField(
            value = activityMedia,
            onValueChange = { activityMedia = it },
            label = { Text(text = "Activity Media (URL)") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 1
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = isComplete,
                onCheckedChange = { isComplete = it }
            )
            Text(text = "Is Complete", modifier = Modifier.padding(start = 8.dp))
        }

        OutlinedTextField(
            value = activityDuedate,
            onValueChange = { activityDuedate = it },
            label = { Text(text = "Activity to be done by") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 1
        )

        OutlinedButton(
            onClick = {
                todoViewModel.createActivity(
                    title = activityTitle,
                    description = activityDescription,
                    media = activityMedia,
                    dueDate = activityDuedate,
                    isComplete = isComplete
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Create Activity")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))

        // Displaying the last created activity or current activity details
        Text(
            text = "Current Activity Status:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(text = "Title: ${activity.title}", modifier = Modifier.align(Alignment.Start))
        Text(text = "Description: ${activity.description}", modifier = Modifier.align(Alignment.Start))
        Text(text = "Media: ${activity.media}", modifier = Modifier.align(Alignment.Start))
        Text(text = "Due Date: ${activity.dueDate}", modifier = Modifier.align(Alignment.Start))
        Text(text = "Status: ${if (activity.isComplete) "Completed" else "Pending"}", modifier = Modifier.align(Alignment.Start))
    }
}

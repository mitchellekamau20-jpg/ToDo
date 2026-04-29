package com.example.todo.ui.theme.screens.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel = viewModel(),
    modifier: Modifier
){ val activity = todoViewModel.activity.collectAsState()
    var activityName by remember { mutableStateOf(TextFieldValue("")) }
    var activityDescription by remember { mutableStateOf(TextFieldValue("")) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = activityName,
            onValueChange = { activityName = it },
            label = { Text(text = "Hobby Name") },
            maxLines = 1
        )
        OutlinedTextField(
            value = activityDescription,
            onValueChange = { activityDescription = it },
            label = { Text(text = "Hobby Description") },
            minLines = 3
        )

        OutlinedButton(
            onClick = {
                todoViewModel.createActivity(
                    name = activityName.text,
                    description = activityDescription.text
                )
            }
        ) {
            Text(text = "Create Activity")
        }
        HorizontalDivider()
        Text(
            text = activity.value.name
        )
        Text(
            text = activity.value.description
        )
    }

}
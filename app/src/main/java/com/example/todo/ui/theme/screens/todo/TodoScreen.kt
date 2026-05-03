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
    var activityTitle by remember { mutableStateOf(TextFieldValue("")) }
    var activityDescription by remember { mutableStateOf(TextFieldValue("")) }
    var activityId by remember { mutableStateOf(TextFieldValue("")) }
    var createdAt by remember { mutableStateOf(TextFieldValue("")) }
    var activityMedia by remember { mutableStateOf(TextFieldValue("")) }
    var isComplete by remember { mutableStateOf(TextFieldValue("")) }
    var activityDuedate  by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = activityId,
            onValueChange = {activityId = it },
            label = { Text(text = "Activity Name") },
            minLines = 1
        )
        OutlinedTextField(
            value = createdAt ,
            onValueChange = {createdAt  = it },
            label = { Text(text = "Activity Name") },
            minLines = 1
        )

        OutlinedTextField(
            value = activityTitle,
            onValueChange = { activityTitle = it },
            label = { Text(text = "Title") },
            maxLines = 1
        )
        OutlinedTextField(
            value = activityDescription,
            onValueChange = {activityDescription = it },
            label = { Text(text = "Activity Description") },
            minLines = 3
        )
        OutlinedTextField(
            value = activityMedia,
            onValueChange = {activityMedia = it },
            label = { Text(text = "Activity Media (URL)") },
            minLines = 1
        )
        //boolean
        Check(isComplete = false)

        OutlinedTextField(
            value = isComplete,
            onValueChange = {isComplete = it },
            label = {  Check(isComplete = false) },
            minLines = 1
        )

        OutlinedTextField(
            value = activityDuedate,
            onValueChange = {activityDuedate = it },
            label = { Text(text = "Activity Duedate") },
            minLines = 1
        )
        //buttons
        //create button
        OutlinedButton(
            onClick = {
                todoViewModel.createActivity(

                    title = activityTitle.text,
                    description = activityDescription.text,
                    media = activityMedia.text,
                    dueDate = activityDuedate.text
                )
            }
        ) {
            Text(text = "Create Activity")
        }
        HorizontalDivider()
        Text(
            text = activity.value.title
        )
        Text(
            text = activity.value.description
        )
        Text(
            text = activity.value.media
        )
        Text(
            text = activity.value.dueDate
        )

    //update
       OutlinedButton(
        onClick = {
            todoViewModel.updateActivity(

                title = activityTitle.text,
                description = activityDescription.text,
                media = activityMedia.text,

            )
        }
    ) {
        Text(text = "update Activity")
    }
    HorizontalDivider()
    Text(
        text = activity.value.title
    )
    Text(
        text = activity.value.description
    )

    Text(
        text = activity.value.dueDate
    )
    //delete
     OutlinedButton(
        onClick = {
            todoViewModel.deleteActivity()
        }
      ) {
        Text(text = "Delete Activity")
       }
       HorizontalDivider()
        Text(
        text = activity.value.title
      )
      Text(
        text = activity.value.description
      )
      Text(
        text = activity.value.media
      )
      Text(
        text = activity.value.dueDate
      )
}
}



















@Composable
fun Check(isComplete: Boolean) {

}

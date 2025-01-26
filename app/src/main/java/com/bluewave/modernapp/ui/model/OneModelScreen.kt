package com.bluewave.modernapp.ui.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OneModelScreen(modifier: Modifier = Modifier, viewmodel: OneModelViewModel = hiltViewModel()) {
    val items by viewmodel.uiState.collectAsStateWithLifecycle()

    if(items is OneModelUiState.Success){
        OneModelScreen(
            items = (items as OneModelUiState.Success).data,
            onSave = viewmodel::addOneModel,
            modifier = modifier
        )
    }
}

@Composable
internal fun OneModelScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
){
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(modifier) {

        var nameOneModel by remember { mutableStateOf("Compose") }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                value = nameOneModel,
                onValueChange = { nameOneModel = it }
            )

            Button(modifier = Modifier.width(150.dp), onClick = { onSave(nameOneModel) }) {
                Text("Save")
            }

            items.forEach {
                Text("Saved item: $it")
            }
        }
    }
}


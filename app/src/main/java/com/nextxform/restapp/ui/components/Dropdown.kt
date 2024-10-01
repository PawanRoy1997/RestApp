package com.nextxform.restapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextxform.restapp.ui.theme.RestAppTheme

@Composable
fun Dropdown(
    currentValue : Item,
    list : List<Item>,
    onSelect : (Item) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 50.dp, minWidth = 80.dp)
                .background(currentValue.color, shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .clickable { showMenu = !showMenu }, contentAlignment = Alignment.Center
    ) {
        Row {
            Spacer(Modifier.width(10.dp))
            Text(currentValue.name, style = MaterialTheme.typography.titleMedium, color = currentValue.textColor)
            Spacer(Modifier.width(10.dp))
        }
        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = !showMenu }) {
            list.forEach {
                DropdownMenuItem(
                        text = {
                            Text(it.name, style = MaterialTheme.typography.titleMedium, color = it.textColor)
                        },
                        onClick = {
                            onSelect.invoke(it)
                            showMenu = !showMenu
                        }
                )
            }
        }
    }
}

data class Item(
    val name : String,
    val color : Color,
        val textColor: Color
)

@Preview
@Composable
fun PreviewDropdown() {
    RestAppTheme {
        val items = listOf<Item>(Item("GET", Color(0x3381C784), Color(0xff81C784)), Item("POST", Color(0x33BA68C8), Color(0xffBA68C8)), Item("PUT", Color(0x33FFB74D), Color(0xffFFB74D)))
        var selected by remember { mutableStateOf(items.first()) }
        Column(modifier = Modifier.fillMaxSize()) {
            Dropdown(selected, items) {
                selected = it
            }
        }
    }
}

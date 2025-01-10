package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
@Suppress("SpreadOperator")
internal fun MultiChoiceAlertDialog(
  items: List<SampleItem>,
  selectedItemKeys: List<String> = emptyList<String>().toList(),
  onItemsSelected: (List<String>) -> Unit,
) {
  val userSelectedItems = remember { mutableStateListOf(*selectedItemKeys.toTypedArray()) }

  AlertDialog(
    onDismissRequest = { onItemsSelected(selectedItemKeys.toSet().toList()) },
    title = { Text(text = "Multi choice") },
    text = {
      Column {
        items.forEach { sampleItem ->
          val isSelected = sampleItem.key in userSelectedItems
          LabelCheckbox(
            item = sampleItem,
            isSelected = isSelected,
            onSelected = {
              if (userSelectedItems.contains(sampleItem.key)) {
                userSelectedItems.remove(sampleItem.key)
              } else {
                userSelectedItems.add(sampleItem.key)
              }
            },
          )
        }
      }
    },
    confirmButton = if (userSelectedItems.isEmpty()) {
      {
        TextButton(
          onClick = { onItemsSelected(emptyList<String>().toList()) },
        ) {
          Text(text = "Cancel")
        }
      }
    } else {
      {
        TextButton(
          onClick = { onItemsSelected(userSelectedItems.toSet().toList()) },
        ) {
          Text(text = "Select")
        }
      }
    },
    dismissButton = if (userSelectedItems.isEmpty()) {
      null
    } else {
      {
        TextButton(
          onClick = { onItemsSelected(emptyList<String>().toList()) },
        ) {
          Text(text = "Clear")
        }
      }
    },
  )
}

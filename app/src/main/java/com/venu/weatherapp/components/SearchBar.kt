package com.venu.weatherapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venu.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    supportingErrorText: String,
    isSearchError: Boolean
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> onTextChange(newText) },
        placeholder = { Text("Search City Name") },
        trailingIcon = {
            IconButton(
                onClick = { onSearch() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,

            ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        isError = isSearchError,
        supportingText = {
            if (isSearchError) Text(
                text = supportingErrorText, color = Color.Red
            )
        }
    )


}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar("", {}, {}, "", false)
}


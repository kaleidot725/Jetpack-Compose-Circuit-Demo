package com.example.demo.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Details(
    state: DetailsScreen.State,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = state.value.toString(),
                fontSize = 100.sp,
                textAlign = TextAlign.Center,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
            )
        }

        Button(
            onClick = { state.eventSink(DetailsScreen.Event.Back) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Back")
        }
    }
}

package com.example.demo.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home(
    state: HomeScreen.State,
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
            if (state.loading) {
                CircularProgressIndicator(
                    modifier =
                        Modifier
                            .size(100.dp)
                            .align(Alignment.Center),
                )
            } else {
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
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.Change(state.value + 1)) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "INCREASE ONE")
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.Change(state.value + 10)) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "INCREASE TEN")
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.Change(state.value - 1)) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "DECREASE ONE")
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.Change(state.value - 10)) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "DECREASE TEN")
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.Randomize) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "RANDOMIZE")
        }

        Button(
            onClick = { state.eventSink(HomeScreen.Event.GoToDetails) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Go to Details")
        }
    }
}

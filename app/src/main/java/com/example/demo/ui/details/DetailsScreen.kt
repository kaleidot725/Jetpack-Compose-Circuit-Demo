package com.example.demo.ui.details

import com.example.demo.ui.home.HomeScreen.Event
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsScreen(
    val value: Int,
) : Screen {
    data class State(
        val value: Int = 0,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data object Back : Event()
    }
}

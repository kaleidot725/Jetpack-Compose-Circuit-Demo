package com.example.demo.screen

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize

@Parcelize
data object CounterScreen : Screen {
    data class State(
        val value: Int = 0,
        val loading: Boolean = false,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event: CircuitUiEvent {
        data class Change(val delta: Int) : Event()
        data object Randomize : Event()
    }
}
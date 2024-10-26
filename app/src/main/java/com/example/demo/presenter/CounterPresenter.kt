package com.example.demo.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.demo.data.RandomService
import com.example.demo.screen.CounterScreen
import com.slack.circuit.runtime.presenter.Presenter
import io.github.takahirom.rin.rememberRetained
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CounterPresenter(
    private val randomService: RandomService,
) : Presenter<CounterScreen.State> {
    @Composable
    override fun present(): CounterScreen.State {
        val scope = rememberCoroutineScope()
        var count by rememberSaveable { mutableIntStateOf(0) }
        var loading by rememberSaveable { mutableStateOf(false) }

        return CounterScreen.State(
            value = count,
            loading = loading
        ) { event ->
            when(event) {
                is CounterScreen.Event.Change -> {
                    count = event.delta
                }
                CounterScreen.Event.Randomize -> {
                    scope.launch {
                        loading = true
                        count = randomService.get(-20, 20)
                        loading = false
                    }
                }
            }
        }
    }
}

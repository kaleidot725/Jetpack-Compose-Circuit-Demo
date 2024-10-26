package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.example.demo.data.RandomService
import com.example.demo.presenter.CounterPresenter
import com.example.demo.screen.CounterScreen
import com.example.demo.ui.CounterApp
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : ComponentActivity() {
    private val scope = CoroutineScope(Main)
    private val randomService = RandomService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val circuit: Circuit = Circuit.Builder()
                .addPresenter<CounterScreen, CounterScreen.State>(CounterPresenter(randomService))
                .addUi<CounterScreen, CounterScreen.State> { state, modifier -> CounterApp(state, modifier) }
                .build()

            setContent {
                CircuitCompositionLocals(circuit) {
                    CircuitContent(CounterScreen)
                }
            }
        }
    }
}

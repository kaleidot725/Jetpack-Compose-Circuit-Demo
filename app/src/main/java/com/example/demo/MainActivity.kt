package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.demo.data.RandomService
import com.example.demo.ui.details.Details
import com.example.demo.ui.details.DetailsPresenter
import com.example.demo.ui.details.DetailsScreen
import com.example.demo.ui.home.Home
import com.example.demo.ui.home.HomePresenter
import com.example.demo.ui.home.HomeScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

class MainActivity : ComponentActivity() {
    private val randomService = RandomService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val circuit: Circuit =
                Circuit
                    .Builder()
                    .addPresenterFactory(HomePresenter.Factory(randomService))
                    .addPresenterFactory(DetailsPresenter.Factory())
                    .addUi<HomeScreen, HomeScreen.State> { state, modifier ->
                        Home(
                            state,
                            modifier,
                        )
                    }.addUi<DetailsScreen, DetailsScreen.State> { state, modifier ->
                        Details(
                            state,
                            modifier,
                        )
                    }.build()

            setContent {
                MaterialTheme {
                    val backStack = rememberSaveableBackStack(HomeScreen)
                    val navigator = rememberCircuitNavigator(backStack)
                    CircuitCompositionLocals(circuit) {
                        NavigableCircuitContent(navigator = navigator, backStack = backStack)
                    }
                }
            }
        }
    }
}

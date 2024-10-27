package com.example.demo.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.demo.data.RandomService
import com.example.demo.ui.details.DetailsScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.coroutines.launch

class HomePresenter(
    private val navigator: Navigator,
    private val randomService: RandomService,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        val scope = rememberCoroutineScope()
        var count by rememberSaveable { mutableIntStateOf(0) }
        var loading by rememberSaveable { mutableStateOf(false) }

        return HomeScreen.State(
            value = count,
            loading = loading,
        ) { event ->
            when (event) {
                is HomeScreen.Event.Change -> {
                    count = event.delta
                }
                HomeScreen.Event.Randomize -> {
                    scope.launch {
                        loading = true
                        count = randomService.get(-20, 20)
                        loading = false
                    }
                }

                HomeScreen.Event.GoToDetails -> {
                    navigator.goTo(DetailsScreen(count))
                }
            }
        }
    }

    class Factory(
        private val randomService: RandomService,
    ) : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is HomeScreen -> return HomePresenter(navigator, randomService)
                else -> null
            }
        }
    }
}

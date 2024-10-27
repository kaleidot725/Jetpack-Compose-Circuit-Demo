package com.example.demo.ui.details

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class DetailsPresenter(
    private val navigator: Navigator,
    private val screen: DetailsScreen,
) : Presenter<DetailsScreen.State> {
    @Composable
    override fun present(): DetailsScreen.State =
        DetailsScreen.State(value = screen.value) {
            when (it) {
                DetailsScreen.Event.Back -> navigator.pop()
            }
        }

    class Factory : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is DetailsScreen -> return DetailsPresenter(navigator, screen)
                else -> null
            }
        }
    }
}

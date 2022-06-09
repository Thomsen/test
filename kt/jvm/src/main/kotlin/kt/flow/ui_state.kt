package kt.flow

import kotlinx.coroutines.flow.MutableStateFlow

sealed class UIState {
    object Success: UIState()
    object Error: UIState()
}


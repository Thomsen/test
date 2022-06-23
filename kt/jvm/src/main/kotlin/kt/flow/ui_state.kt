package kt.flow

sealed class UIState {
    object Success: UIState()
    object Error: UIState()
}


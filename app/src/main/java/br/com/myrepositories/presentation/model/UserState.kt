package br.com.myrepositories.presentation.model

import br.com.myrepositories.domain.models.User

sealed class UserState{
    object Loading: UserState()
    data class ScreenData(val screenData: User) : UserState()
    data class Error(val exception: Throwable? = null) : UserState()
}

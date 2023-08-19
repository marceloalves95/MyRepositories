package br.com.myrepositories.presentation.model

import br.com.myrepositories.domain.models.MyRepositories

sealed class MyRepositoriesState {
    object Loading : MyRepositoriesState()
    data class ScreenData(val screenData: List<MyRepositories>) : MyRepositoriesState()
    data class Error(val exception: Throwable? = null) : MyRepositoriesState()
}

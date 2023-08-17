package br.com.myrepositories.presentation.ui

import androidx.lifecycle.ViewModel
import br.com.myrepositories.domain.usecases.GetAllMyRepositoriesUseCase
import br.com.myrepositories.domain.usecases.GetUserUseCase
import br.com.myrepositories.extensions.others.launch
import br.com.myrepositories.network.event.Event
import br.com.myrepositories.presentation.model.MyRepositoriesState
import br.com.myrepositories.presentation.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMyRepositoriesUseCase: GetAllMyRepositoriesUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MyRepositoriesState>(MyRepositoriesState.Loading)
    val state: StateFlow<MyRepositoriesState> get() = _state

    private val _stateUser = MutableStateFlow<UserState>(UserState.Loading)
    val stateUser: StateFlow<UserState> get() = _stateUser

    fun loadScreen() = launch {
        getAllMyRepositoriesUseCase.invoke().collect { event ->
            when (event) {
                is Event.Loading -> {
                    _state.value = MyRepositoriesState.Loading
                }

                is Event.Data -> {
                    _state.value = MyRepositoriesState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _state.value = MyRepositoriesState.Error(event.error)
                }
            }
        }
    }

    fun loadUser() = launch {
        getUserUseCase.invoke().collect { event ->
            when (event) {
                is Event.Loading -> {
                    _stateUser.value = UserState.Loading
                }

                is Event.Data -> {
                    _stateUser.value = UserState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _stateUser.value = UserState.Error(event.error)
                }
            }
        }
    }
}
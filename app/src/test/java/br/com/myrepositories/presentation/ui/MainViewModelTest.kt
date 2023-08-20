package br.com.myrepositories.presentation.ui

import br.com.myrepositories.domain.model.dummyMyRepositories
import br.com.myrepositories.domain.usecases.GetAllMyRepositoriesUseCase
import br.com.myrepositories.domain.usecases.GetUserUseCase
import br.com.myrepositories.network.event.Event
import br.com.myrepositories.presentation.model.MyRepositoriesState
import br.com.myrepositories.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.junit.Before
import org.junit.Test

class MainViewModelTest : BaseTest(){

    @RelaxedMockK
    private lateinit var getAllMyRepositoriesUseCase: GetAllMyRepositoriesUseCase

    @RelaxedMockK
    private lateinit var getUserUseCase: GetUserUseCase

    @RelaxedMockK
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel(
            getAllMyRepositoriesUseCase = getAllMyRepositoriesUseCase,
            getUserUseCase = getUserUseCase
        )
    }

    @Test
    fun `should load repositories when it is called with success`() = runBlocking {

        //Arrange
        val state = MutableStateFlow<MyRepositoriesState>(MyRepositoriesState.Loading)
        val result = mutableListOf<MyRepositoriesState>()
        val emitJob:Job = launch {
            state.toList(result)
        }

        launch {
            state.tryEmit(MyRepositoriesState.Loading)
            state.tryEmit(MyRepositoriesState.ScreenData(listOf(dummyMyRepositories)))
            yield()
            emitJob.cancel()
        }

        coEvery {
            getAllMyRepositoriesUseCase.invoke() } returns flowOf(
            Event.loading(isLoading = true),
            Event.data(listOf(dummyMyRepositories))
        )

        //Act
        viewModel.loadAllRepositories()

        //Assert
        coVerify(exactly = 1) {
            getAllMyRepositoriesUseCase.invoke()
        }

        confirmVerified(getAllMyRepositoriesUseCase)
    }

    @Test
    fun `should load repositories when it is called with failure`() = runBlocking {

        //Arrange
        val error = mockk<Throwable>(relaxed = true)
        val state = MutableStateFlow<MyRepositoriesState>(MyRepositoriesState.Loading)
        val result = mutableListOf<MyRepositoriesState>()
        val emitJob:Job = launch {
            state.toList(result)
        }

        launch {
            state.tryEmit(MyRepositoriesState.Loading)
            state.tryEmit(MyRepositoriesState.Error(exception = error))
            yield()
            emitJob.cancel()
        }

        coEvery {
            getAllMyRepositoriesUseCase.invoke() } returns flowOf(
            Event.loading(isLoading = true),
            Event.error(error)
        )

        //Act
        viewModel.loadAllRepositories()

        //Assert
        coVerify(exactly = 1) {
            getAllMyRepositoriesUseCase.invoke()
        }

        confirmVerified(getAllMyRepositoriesUseCase)
    }
}
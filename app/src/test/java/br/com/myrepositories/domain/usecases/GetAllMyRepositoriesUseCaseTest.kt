package br.com.myrepositories.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.myrepositories.domain.model.dummyMyRepositories
import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.domain.repository.MyRepository
import br.com.myrepositories.network.event.Event
import br.com.myrepositories.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllMyRepositoriesUseCaseTest : BaseTest() {

    @RelaxedMockK
    private lateinit var getAllMyRepositoriesUseCase: GetAllMyRepositoriesUseCase

    @MockK
    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        getAllMyRepositoriesUseCase = GetAllMyRepositoriesUseCase(repository)
    }

    @Test
    fun `should GetAllMyRepositoriesUseCase when it is called with success`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<List<MyRepositories>>> = mutableListOf()

        coEvery {
            repository.getAllRepository()
        } returns listOf(dummyMyRepositories)

        //Act
        getAllMyRepositoriesUseCase.invoke().collect { event ->
            progressEmit.add(event)
        }

        //Assert
        assertThat(progressEmit).isEqualTo(
            mutableListOf(
                Event.Loading,
                Event.Data(listOf(dummyMyRepositories))
            )
        )

        coVerify {
            repository.getAllRepository()
        }

        confirmVerified(repository)
    }

    @Test(expected = Throwable::class)
    fun `should GetAllMyRepositoriesUseCase when it is called with failure`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<List<MyRepositories>>> = mutableListOf()
        val error = mockk<Throwable>(relaxed = true)

        coEvery {
            repository.getAllRepository()
        } throws error

        //Act
        getAllMyRepositoriesUseCase.invoke().collect { event ->
            progressEmit.add(event)
        }

        //Assert
        assertThat(progressEmit).isEqualTo(
            mutableListOf(
                Event.Loading,
                Event.Error(error)
            )
        )

        coVerify {
            repository.getAllRepository()
        }

        confirmVerified(repository)
    }
}
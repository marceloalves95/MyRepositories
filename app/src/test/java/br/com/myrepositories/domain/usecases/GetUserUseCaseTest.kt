package br.com.myrepositories.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.myrepositories.domain.model.dummyUser
import br.com.myrepositories.domain.models.User
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

class GetUserUseCaseTest : BaseTest() {

    @RelaxedMockK
    private lateinit var getUserUseCase: GetUserUseCase

    @MockK
    private lateinit var repository: MyRepository

    @Before
    fun setup() {
        getUserUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `should GetUserUseCase when it is called with success`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<User>> = mutableListOf()

        coEvery {
            repository.getUser()
        } returns dummyUser

        //Act
        getUserUseCase.invoke().collect { event ->
            progressEmit.add(event)
        }

        //Assert
        assertThat(progressEmit).isEqualTo(
            mutableListOf(
                Event.Loading,
                Event.Data(dummyUser)
            )
        )

        coVerify {
            repository.getUser()
        }

        confirmVerified(repository)
    }

    @Test(expected = Throwable::class)
    fun `should GetUserUseCase when it is called with failure`() = runBlocking {

        //Arrange
        val progressEmit: MutableList<Event<User>> = mutableListOf()
        val error = mockk<Throwable>(relaxed = true)

        coEvery {
            repository.getUser()
        } throws error

        //Act
        getUserUseCase.invoke().collect { event ->
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
            repository.getUser()
        }

        confirmVerified(repository)
    }

}
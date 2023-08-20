package br.com.myrepositories.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.myrepositories.data.api.MyRepositoriesApi
import br.com.myrepositories.data.mapper.toMyRepositories
import br.com.myrepositories.data.model.MyRepositoriesResponse
import br.com.myrepositories.data.model.dummyMyRepositories
import br.com.myrepositories.testing.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class MyRepositoryImplTest : BaseTest() {

    @RelaxedMockK
    private lateinit var repository: MyRepositoryImpl

    @RelaxedMockK
    private lateinit var repositoriesApi: MyRepositoriesApi

    @Before
    fun setup() {
        repository = MyRepositoryImpl(repositoriesApi)
    }

    @Test
    fun `should get all repositories when is called with success`() = runBlocking {

        //Arrange
        val response = Response.success(listOf(dummyMyRepositories))

        coEvery {
            repositoriesApi.getAllRepository()
        } returns response

        //Act
        val result = repository.getAllRepository()

        //Assert
        assertThat(result).isEqualTo(listOf(dummyMyRepositories.toMyRepositories()))

        coVerify(exactly = 1) {
            repositoriesApi.getAllRepository()
        }

        confirmVerified(repositoriesApi)
    }

    @Test(expected = HttpException::class)
    fun `should get all repositories when is called with failure`() = runBlocking {

        //Arrange
        val responseError = Response.error<MyRepositoriesResponse>(
            500,
            "some content".toResponseBody("plain/text".toMediaTypeOrNull())
        )

        coEvery {
            repositoriesApi.getAllRepository()
        } throws HttpException(responseError)

        //Act
        repository.getAllRepository()

        //Assert
        coVerify(exactly = 1) {
            repositoriesApi.getAllRepository()
        }

        confirmVerified(repositoriesApi)
    }
}
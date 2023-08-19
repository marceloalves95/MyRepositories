package br.com.myrepositories.data.api

import br.com.myrepositories.data.model.MyRepositoriesResponse
import br.com.myrepositories.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyRepositoriesApi {
    @GET("users/marceloalves95/repos")
    suspend fun getAllRepository(): Response<List<MyRepositoriesResponse>>

    @GET("users/marceloalves95")
    suspend fun getUser(): Response<UserResponse>
}
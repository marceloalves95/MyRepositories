package br.com.myrepositories.data

import br.com.myrepositories.data.api.MyRepositoriesApi
import br.com.myrepositories.data.mapper.toMyRepositories
import br.com.myrepositories.data.mapper.toUser
import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.domain.models.User
import br.com.myrepositories.domain.repository.MyRepository
import br.com.myrepositories.extensions.network.parseResponse
import br.com.myrepositories.extensions.network.toResponse
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myRepositoriesApi: MyRepositoriesApi
) : MyRepository {
    override suspend fun getAllRepository(): List<MyRepositories> {
        return myRepositoriesApi.getAllRepository().parseResponse().toResponse()
            .map { it.toMyRepositories() }
    }

    override suspend fun getUser(): User {
        return myRepositoriesApi.getUser().parseResponse().toResponse().toUser()
    }
}
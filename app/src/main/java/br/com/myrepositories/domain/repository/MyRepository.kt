package br.com.myrepositories.domain.repository

import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.domain.models.User
import dagger.Provides
import javax.inject.Singleton

interface MyRepository {
    suspend fun getAllRepository(): List<MyRepositories>
    suspend fun getUser(): User
}
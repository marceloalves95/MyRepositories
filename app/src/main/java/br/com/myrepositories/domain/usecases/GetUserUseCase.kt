package br.com.myrepositories.domain.usecases

import br.com.myrepositories.domain.repository.MyRepository
import br.com.myrepositories.extensions.others.executeFlow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke() = executeFlow(
        getRepository = {
            repository.getUser()
        }
    )
}
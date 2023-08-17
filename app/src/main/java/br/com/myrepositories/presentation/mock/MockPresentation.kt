package br.com.myrepositories.presentation.mock

import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.domain.models.Owner
import br.com.myrepositories.domain.models.User

val dummyUser = User(
    avatarUrl = "https://avatars.githubusercontent.com/u/67604155?v=4",
    bio = "Desenvolvedor Android | Kotlin",
    createdAt = "2020-06-29T18:43:54Z",
    email = null,
    followers = 63,
    following = 33,
    htmlUrl = "https://github.com/marceloalves95",
    location = "São Paulo",
    login = "marceloalves95",
    name = "Marcelo Alves",
    publicGists = 0,
    publicRepos = 53,
    updatedAt = "2023-08-01T00:53:30Z",
    url = "url"
)
val dummyOwner = Owner(
    avatarUrl = "https://avatars.githubusercontent.com/u/67604155?v=4",
    htmlUrl = "https://github.com/marceloalves95",
    login = "marceloalves95",
    reposUrl = "https://api.github.com/users/marceloalves95/repos",
    url = "https://api.github.com/users/marceloalves95"
)

val dummyMyRepositories = MyRepositories(
    createdAt = "2020-10-25T15:04:38Z",
    description = "Projeto feito com a linguagem Kotlin, e com funcionalidades básicas de um SGDB (Sistema Gerenciador de Banco de Dados) usando o banco de dados SQLite.",
    fullName = "marceloalves95/Agenda-Telefonica",
    language = "Kotlin",
    name = "Agenda-Telefonica",
    owner = dummyOwner,
    pushedAt = "2021-06-29T20:26:23Z",
    size = 561,
    updatedAt = "2021-06-29T20:26:26Z",
    url = "https://api.github.com/repos/marceloalves95/Agenda-Telefonica"
)
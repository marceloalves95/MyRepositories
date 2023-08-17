package br.com.myrepositories.domain.models

data class MyRepositories(
    val createdAt: String?,
    val description: String?,
    val fullName: String?,
    val language: String?,
    val name: String?,
    val owner: Owner?,
    val pushedAt: String?,
    val size: Int?,
    val updatedAt: String?,
    val url: String?
)

data class Owner(
    val avatarUrl: String?,
    val htmlUrl: String?,
    val login: String?,
    val reposUrl: String?,
    val url: String?
)

data class User(
    val avatarUrl: String?,
    val bio: String?,
    val createdAt: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val htmlUrl: String?,
    val publicGists: Int?,
    val publicRepos: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val updatedAt: String?,
    val url: String?
)
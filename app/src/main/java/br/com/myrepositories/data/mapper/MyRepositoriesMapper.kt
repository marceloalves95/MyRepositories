package br.com.myrepositories.data.mapper

import br.com.myrepositories.data.model.MyRepositoriesResponse
import br.com.myrepositories.data.model.OwnerResponse
import br.com.myrepositories.data.model.UserResponse
import br.com.myrepositories.domain.models.MyRepositories
import br.com.myrepositories.domain.models.Owner
import br.com.myrepositories.domain.models.User

internal fun MyRepositoriesResponse.toMyRepositories() = MyRepositories(
    createdAt = createdAt,
    description = description,
    fullName = fullName,
    language = language,
    name = name,
    owner = owner?.toOwner(),
    pushedAt = pushedAt,
    size = size,
    updatedAt = updatedAt,
    url = url
)

internal fun OwnerResponse.toOwner() = Owner(
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    login = login,
    reposUrl = reposUrl,
    url = url
)

internal fun UserResponse.toUser() = User(
    avatarUrl = avatarUrl,
    bio = bio,
    createdAt = createdAt,
    email = email,
    followers = followers,
    following = following,
    htmlUrl = htmlUrl,
    location = location,
    login = login,
    name = name,
    publicGists = publicGists,
    publicRepos = publicRepos,
    updatedAt = updatedAt,
    url = url
)
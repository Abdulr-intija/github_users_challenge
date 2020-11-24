package com.intija.domain.repo

import com.intija.domain.entities.GithubSearchUserEntity
import io.reactivex.Single

interface SearchGithubUsersRepository {
    fun searchGithubUsers(query: String, perPage: Int, page: Int): Single<List<GithubSearchUserEntity>>
}
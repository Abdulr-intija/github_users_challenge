package com.intija.domain.repo

import com.intija.domain.entities.GithubUserEntity
import io.reactivex.Single

interface GetGithubUserRepository {
    fun getGithubUser(userName: String): Single<GithubUserEntity>
}
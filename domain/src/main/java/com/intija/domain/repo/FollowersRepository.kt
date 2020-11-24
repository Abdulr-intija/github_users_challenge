package com.intija.domain.repo

import com.intija.domain.entities.FollowerEntity
import io.reactivex.Single

interface FollowersRepository {
    fun getFollowers(userName: String, perPage: Int, page: Int): Single<List<FollowerEntity>>
    fun getFollowing(userName: String, perPage: Int, page: Int): Single<List<FollowerEntity>>
}
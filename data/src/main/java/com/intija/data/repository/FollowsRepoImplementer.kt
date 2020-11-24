package com.intija.data.repository

import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.FollowsMapper
import com.intija.domain.entities.FollowerEntity
import com.intija.domain.repo.FollowersRepository
import io.reactivex.Single
import javax.inject.Inject

class FollowsRepoImplementer @Inject constructor(
    private val serviceAPI: ServiceAPI,
    private val dataToDomainMapper: FollowsMapper): FollowersRepository {
    override fun getFollowers(
        userName: String,
        perPage: Int,
        page: Int
    ): Single<List<FollowerEntity>> {
        return serviceAPI.getFollowers(userName, perPage, page).map { response ->
            response.map {followerEntity ->
                dataToDomainMapper.mapFromDataToDomain(followerEntity)
            }
        }
    }

    override fun getFollowing(
        userName: String,
        perPage: Int,
        page: Int
    ): Single<List<FollowerEntity>> {
        return serviceAPI.getFollowing(userName, perPage, page).map { response ->
            response.map {followingEntity ->
                dataToDomainMapper.mapFromDataToDomain(followingEntity)
            }
        }
    }
}
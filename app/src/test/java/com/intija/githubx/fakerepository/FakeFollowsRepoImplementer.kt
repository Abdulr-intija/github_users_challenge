package com.intija.githubx.fakerepository

import com.intija.domain.entities.FollowerEntity
import com.intija.domain.repo.FollowersRepository
import io.reactivex.Single

class FakeFollowsRepoImplementer: FollowersRepository {
    private var searchResults = mutableListOf<FollowerEntity>()
    private var singleSearchResultsEntity: Single<List<FollowerEntity>>? = null

    override fun getFollowers(
        userName: String,
        perPage: Int,
        page: Int
    ): Single<List<FollowerEntity>> {
        refreshData(userName)
        return singleSearchResultsEntity!!
    }

    override fun getFollowing(
        userName: String,
        perPage: Int,
        page: Int
    ): Single<List<FollowerEntity>> {
        refreshData(userName)
        return singleSearchResultsEntity!!
    }


    private fun refreshData(usernameQuery: String){
        searchResults.add(initGithubUser(usernameQuery)!!)
        singleSearchResultsEntity = Single.just(searchResults)
    }

    private fun initGithubUser(username: String): FollowerEntity? {
        return FollowerEntity(
            "https://testurl.com",
            "Test bio",
            "Tst blog",
            "Test company",
            "2020-10-10",
            "test@email.com",
            "https://test.url",
            0,
            username,
            "20",
            "https://following.url",
            "https://test.url",
            "3312",
            false,
            "https://test.url",
            "https://test.url",
            "https://test.url",
            "https://test.url"
        )
    }




}
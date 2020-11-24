package com.intija.githubx.fakerepository

import com.intija.domain.entities.GithubSearchUserEntity
import com.intija.domain.repo.SearchGithubUsersRepository
import io.reactivex.Single

class FakeSearchGithubUserRepoImplementer: SearchGithubUsersRepository {
    private var searchResults = mutableListOf<GithubSearchUserEntity>()
    private var singleSearchResultsEntity: Single<List<GithubSearchUserEntity>>? = null

    override fun searchGithubUsers(
        query: String,
        perPage: Int,
        page: Int
    ): Single<List<GithubSearchUserEntity>> {
        refreshData(query)
        return singleSearchResultsEntity!!
    }


    private fun refreshData(usernameQuery: String){
        searchResults.add(initGithubUser(usernameQuery)!!)
        singleSearchResultsEntity = Single.just(searchResults)
    }

    private fun initGithubUser(username: String): GithubSearchUserEntity? {
        return GithubSearchUserEntity(
            "https://testurl.com",
            "Test bio",
            "Tst blog",
            "Test company",
            "2020-10-10",
            "test@email.com",
            "https://test.url",
            0,
            "https://followers.url",
            "20",
            "https://following.url",
            "https://test.url",
            "3312",
            0,
            false,
            "https://test.url",
            "https://test.url",
            username,
            "https://test.url",
        )
    }


}
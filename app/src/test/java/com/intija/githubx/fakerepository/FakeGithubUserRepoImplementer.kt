package com.intija.githubx.fakerepository

import com.intija.domain.entities.GithubUserEntity
import com.intija.domain.repo.GetGithubUserRepository
import io.reactivex.Single

class FakeGithubUserRepoImplementer: GetGithubUserRepository {
    private var githubUser: GithubUserEntity? = null
    private var singleGithubUserEntity: Single<GithubUserEntity>? = null

    override fun getGithubUser(userName: String): Single<GithubUserEntity> {
        refreshData(userName)
        return singleGithubUserEntity!!
    }


    fun refreshData(username: String){
        githubUser = initGithubUser(username)
        singleGithubUserEntity = Single.just(githubUser)
    }

    private fun initGithubUser(username: String): GithubUserEntity? {
        return GithubUserEntity(
            "https://testurl.com",
            "Test bio",
            "Tst blog",
            "Test company",
            "2020-10-10",
            "test@email.com",
            "https://test.url",
            0,
            "https://followers.url",
            20,
            "https://following.url",
            "https://test.url",
            "3312",
            true,
            "https://test.url",
            21312,
            "Test location",
            username,
            "Test name",
            "3243",
            "https://test.url",
            324,
            23,
            "https://test.url",
            "https://test.url",
            false,
            "https://test.url",
            "https://test.url",
            "testTwitter",
            "test",
            "2020-12-15",
            "https://test.url",
        )
    }
}
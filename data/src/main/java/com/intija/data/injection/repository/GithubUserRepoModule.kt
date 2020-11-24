package com.intija.data.injection.repository

import com.intija.data.datasource.local.DatabaseDAO
import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.GithubUserMapper
import com.intija.data.repository.GithubUserRepoImplementer
import com.intija.domain.repo.GetGithubUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubUserRepoModule {

    @Singleton
    @Provides
    fun provideGithubUserMapper() = GithubUserMapper()

    @Singleton
    @Provides
    fun providesGithubUserRepository(service: ServiceAPI, dao: DatabaseDAO, userMapper: GithubUserMapper): GetGithubUserRepository {
        return GithubUserRepoImplementer(service, dao, userMapper)
    }
}
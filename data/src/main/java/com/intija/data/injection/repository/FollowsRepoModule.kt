package com.intija.data.injection.repository

import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.FollowsMapper
import com.intija.data.repository.FollowsRepoImplementer
import com.intija.domain.repo.FollowersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FollowsRepoModule {

    @Singleton
    @Provides
    fun provideFollowsMapper() = FollowsMapper()

    @Singleton
    @Provides
    fun providesFollowsRepository(service: ServiceAPI, followsMapper: FollowsMapper): FollowersRepository {
        return FollowsRepoImplementer(service, followsMapper)
    }
}
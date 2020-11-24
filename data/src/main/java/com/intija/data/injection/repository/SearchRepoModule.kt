package com.intija.data.injection.repository

import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.SearchMapper
import com.intija.data.repository.SearchRepoImplementer
import com.intija.domain.repo.SearchGithubUsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SearchRepoModule {

    @Singleton
    @Provides
    fun provideSearchMapper() = SearchMapper()

    @Singleton
    @Provides
    fun providesSearchRepository(service: ServiceAPI, searchMapper: SearchMapper): SearchGithubUsersRepository {
        return SearchRepoImplementer(service, searchMapper)
    }
}
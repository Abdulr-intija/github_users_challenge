package com.intija.domain

import com.intija.domain.repo.FollowersRepository
import com.intija.domain.repo.GetGithubUserRepository
import com.intija.domain.repo.SearchGithubUsersRepository
import com.intija.domain.usecases.GetFollowersUseCase
import com.intija.domain.usecases.GetFollowingUseCase
import com.intija.domain.usecases.GetGithubUserUseCase
import com.intija.domain.usecases.SearchGithubUsersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun provideSearchGithubUsersUseCase(repository: SearchGithubUsersRepository) = SearchGithubUsersUseCase(repository)

    @Singleton
    @Provides
    fun provideGetGithubUserUseCase(repository: GetGithubUserRepository) = GetGithubUserUseCase(repository)

    @Singleton
    @Provides
    fun provideGetFollowersUseCase(repository: FollowersRepository) = GetFollowersUseCase(repository)

    @Singleton
    @Provides
    fun provideGetFollowingUseCase(repository: FollowersRepository) = GetFollowingUseCase(repository)
}
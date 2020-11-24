package com.intija.githubx.injection

import com.intija.githubx.mappers.FollowsMapper
import com.intija.githubx.mappers.GithubUserMapper
import com.intija.githubx.mappers.SearchMapper
import com.intija.githubx.viewmodel.BaseSchedulerProvider
import com.intija.githubx.viewmodel.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideTaskBaseScheduler(): BaseSchedulerProvider = SchedulerProvider()

    @Singleton
    @Provides
    fun provideSearchMapper() = SearchMapper()

    @Singleton
    @Provides
    fun provideGithubUserMapper() = GithubUserMapper()

    @Singleton
    @Provides
    fun provideFollowsMapper() = FollowsMapper()
}
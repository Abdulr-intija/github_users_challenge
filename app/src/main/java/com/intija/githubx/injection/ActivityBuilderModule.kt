package com.intija.githubx.injection

import com.intija.githubx.views.follows.FollowersViewModelModule
import com.intija.githubx.views.follows.FollowingViewModelModule
import com.intija.githubx.views.follows.Follows
import com.intija.githubx.views.githubuser.GithubUser
import com.intija.githubx.views.githubuser.GithubUserViewModelModule
import com.intija.githubx.views.search.SearchActivity
import com.intija.githubx.views.search.SearchViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [SearchViewModelModule::class])
    internal abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [GithubUserViewModelModule::class])
    internal abstract fun bindGithubUserActivity(): GithubUser

    @ContributesAndroidInjector(modules = [FollowersViewModelModule::class, FollowingViewModelModule::class])
    internal abstract fun bindFollowsActivity(): Follows
}
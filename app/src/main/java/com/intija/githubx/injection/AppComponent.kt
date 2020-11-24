package com.intija.githubx.injection

import android.app.Application
import com.intija.data.injection.DatabaseModule
import com.intija.data.injection.RetrofitModule
import com.intija.data.injection.repository.FollowsRepoModule
import com.intija.data.injection.repository.GithubUserRepoModule
import com.intija.data.injection.repository.SearchRepoModule
import com.intija.domain.UseCasesModule
import com.intija.githubx.GithubX
import com.intija.githubx.viewmodel.ViewModelFactory
import com.intija.githubx.views.follows.FollowersViewModelModule
import com.intija.githubx.views.follows.FollowingViewModelModule
import com.intija.githubx.views.githubuser.GithubUserViewModelModule
import com.intija.githubx.views.search.SearchViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactory::class,
        ActivityBuilderModule::class,
        AppModule::class,
        UseCasesModule::class,
        RetrofitModule::class,
        DatabaseModule::class,

        //search
        SearchViewModelModule::class,
        SearchRepoModule::class,

        //Github user
        GithubUserViewModelModule::class,
        GithubUserRepoModule::class,

        //Follower & Following
        FollowersViewModelModule::class,
        FollowingViewModelModule::class,
        FollowsRepoModule::class
    ]
)

interface AppComponent: AndroidInjector<GithubX> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
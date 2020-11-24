package com.intija.githubx.views.follows

import androidx.lifecycle.ViewModel
import com.intija.githubx.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FollowersViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FollowersViewModel::class)
    abstract fun bindGithubUserViewModel(followersViewModel: FollowersViewModel):  ViewModel
}
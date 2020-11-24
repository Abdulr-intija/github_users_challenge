package com.intija.githubx.views.follows

import androidx.lifecycle.ViewModel
import com.intija.githubx.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FollowingViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FollowingViewModel::class)
    abstract fun bindGithubUserViewModel(followingViewModel: FollowingViewModel):  ViewModel
}
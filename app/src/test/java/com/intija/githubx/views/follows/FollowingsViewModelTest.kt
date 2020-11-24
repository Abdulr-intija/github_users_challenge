package com.intija.githubx.views.follows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.intija.domain.usecases.GetFollowingUseCase
import com.intija.githubx.fakerepository.FakeFollowsRepoImplementer
import com.intija.githubx.mappers.FollowsMapper
import com.intija.githubx.viewmodel.BaseSchedulerProviderTest
import com.intija.githubx.viewmodel.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FollowingsViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var followingViewModel: FollowingViewModel

    @Before
    fun setup() {
        followingViewModel = FollowingViewModel(
            GetFollowingUseCase(
                FakeFollowsRepoImplementer() //Dummy access to data sources for test
            ),
                FollowsMapper(),
                BaseSchedulerProviderTest()
        )
    }

    @Test
    fun `search github users from search view model`(){
        followingViewModel.getFollowing("intija", 1)

        val result = followingViewModel.followings.getOrAwaitValue()

        assertThat(result.data).isNotNull()
        assertThat(result.data!!.size).isGreaterThan(0)
    }
}
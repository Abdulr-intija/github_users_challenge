package com.intija.githubx.views.follows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.intija.domain.usecases.GetFollowersUseCase
import com.intija.githubx.fakerepository.FakeFollowsRepoImplementer
import com.intija.githubx.mappers.FollowsMapper
import com.intija.githubx.viewmodel.BaseSchedulerProviderTest
import com.intija.githubx.viewmodel.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FollowersViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var followersViewModel: FollowersViewModel

    @Before
    fun setup() {
        followersViewModel = FollowersViewModel(
            GetFollowersUseCase(
                FakeFollowsRepoImplementer() //Dummy access to data sources for test
            ),
                FollowsMapper(),
                BaseSchedulerProviderTest()
        )
    }

    @Test
    fun `search github users from search view model`(){
        followersViewModel.getFollowers("intija", 1)

        val result = followersViewModel.followers.getOrAwaitValue()

        assertThat(result.data).isNotNull()
        assertThat(result.data!!.size).isGreaterThan(0)
    }
}
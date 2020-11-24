package com.intija.githubx.views.githubuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.intija.domain.usecases.GetGithubUserUseCase
import com.intija.githubx.mappers.GithubUserMapper
import com.intija.githubx.utils.ViewState
import com.intija.githubx.fakerepository.FakeGithubUserRepoImplementer
import com.intija.githubx.viewmodel.BaseSchedulerProviderTest
import com.intija.githubx.viewmodel.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GithubUserViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var githubUserViewModel: GithubUserViewModel

    @Before
    fun setup() {
        githubUserViewModel = GithubUserViewModel(
            GetGithubUserUseCase(
                FakeGithubUserRepoImplementer() //Dummy access to data sources for test
            ),
                GithubUserMapper(),
                BaseSchedulerProviderTest()
        )
    }

    @Test
    fun `get github user from view model`(){
        githubUserViewModel.getGithubUser("intija")

        val githubUser = githubUserViewModel.result.getOrAwaitValue()

        assertThat(githubUser.status).isEqualTo(ViewState.Status.SUCCESS)
    }
}
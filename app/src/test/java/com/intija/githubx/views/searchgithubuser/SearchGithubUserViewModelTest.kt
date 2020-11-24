package com.intija.githubx.views.searchgithubuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.intija.domain.usecases.SearchGithubUsersUseCase
import com.intija.githubx.fakerepository.FakeSearchGithubUserRepoImplementer
import com.intija.githubx.mappers.SearchMapper
import com.intija.githubx.viewmodel.BaseSchedulerProviderTest
import com.intija.githubx.viewmodel.getOrAwaitValue
import com.intija.githubx.views.search.SearchViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchGithubUserViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(
            SearchGithubUsersUseCase(
                FakeSearchGithubUserRepoImplementer() //Dummy access to data sources for test
            ),
                SearchMapper(),
                BaseSchedulerProviderTest()
        )
    }

    @Test
    fun `search github users from search view model`(){
        searchViewModel.searchUsers("intija")

        val result = searchViewModel.searchResults.getOrAwaitValue()

        assertThat(result.data).isNotNull()
        assertThat(result.data!!.size).isGreaterThan(0)
    }
}
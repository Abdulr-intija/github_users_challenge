package com.intija.domain.usecases

import com.intija.domain.Constants
import com.intija.domain.repo.SearchGithubUsersRepository
import javax.inject.Inject

class SearchGithubUsersUseCase @Inject constructor(private val repository: SearchGithubUsersRepository) {
    fun searchGithubUsers(query: String) = repository.searchGithubUsers(query, Constants.searchResultsPerPage, Constants.searchResultPage)
}
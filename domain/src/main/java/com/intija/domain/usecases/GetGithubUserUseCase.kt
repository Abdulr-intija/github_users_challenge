package com.intija.domain.usecases

import com.intija.domain.repo.GetGithubUserRepository
import javax.inject.Inject

class GetGithubUserUseCase @Inject constructor(private val repository: GetGithubUserRepository) {
    fun getGithubUser(userName: String) = repository.getGithubUser(userName)
}
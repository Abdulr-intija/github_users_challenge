package com.intija.domain.usecases

import com.intija.domain.Constants
import com.intija.domain.repo.FollowersRepository
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(private val repository: FollowersRepository) {
    fun getFollowers(userName: String, page: Int) = repository.getFollowers(userName, Constants.followersPerPage, page)
}
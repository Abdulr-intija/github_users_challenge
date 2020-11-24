package com.intija.domain.usecases

import com.intija.domain.Constants
import com.intija.domain.repo.FollowersRepository
import javax.inject.Inject

class GetFollowingUseCase @Inject constructor(private val repository: FollowersRepository) {
    fun getFollowing(userName: String, page: Int) = repository.getFollowing(userName, Constants.followersPerPage, page)
}
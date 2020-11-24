package com.intija.data.repository

import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.SearchMapper
import com.intija.data.models.search.Item
import com.intija.data.models.search.SearchResponse
import com.intija.domain.entities.GithubSearchUserEntity
import com.intija.domain.repo.SearchGithubUsersRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepoImplementer @Inject constructor(
    private val serviceAPI: ServiceAPI,
    private val dataToDomainMapper: SearchMapper
): SearchGithubUsersRepository {
    override fun searchGithubUsers(
        query: String,
        perPage: Int,
        page: Int
    ): Single<List<GithubSearchUserEntity>> {
        return serviceAPI.searchGithubUsers(query, perPage, page).map { response: SearchResponse ->
            response.items.map { githubSearchUserEntity: Item ->
                dataToDomainMapper.mapFromDataToDomain(githubSearchUserEntity)
            }
        }
    }
}
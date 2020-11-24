package com.intija.data.repository

import com.intija.data.datasource.local.DatabaseDAO
import com.intija.data.datasource.remote.ServiceAPI
import com.intija.data.mappers.GithubUserMapper
import com.intija.domain.entities.GithubUserEntity
import com.intija.domain.repo.GetGithubUserRepository
import io.reactivex.Single
import javax.inject.Inject

class GithubUserRepoImplementer @Inject constructor(
    private val serviceAPI: ServiceAPI,
    private val databaseDAO: DatabaseDAO,
    private val dataToDomainMapper: GithubUserMapper
): GetGithubUserRepository {
    override fun getGithubUser(userName: String): Single<GithubUserEntity> {

        return databaseDAO.getGithubUser(userName).map { response ->
            //sync room DB with server whilst returning room DB data to UI
            serviceAPI.getGithubUser(userName).map {
                databaseDAO.insertGithubUser(it)
            }

            //reliable data retrieved from RoomDB
            dataToDomainMapper.mapFromDataToDomain(response)

        }.onErrorResumeNext{
            //room DB data not reliable. Hence fetch from server
            serviceAPI.getGithubUser(userName).map { response ->
                //sync with room DB
                databaseDAO.insertGithubUser(response)

                dataToDomainMapper.mapFromDataToDomain(response)
            }
        }

    }
}
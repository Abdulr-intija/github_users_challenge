package com.intija.data.datasource.remote

import com.intija.data.models.followers.FollowersResponse
import com.intija.data.models.search.SearchResponse
import com.intija.data.models.user.GetUserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {
    @GET("/search/users")
    fun searchGithubUsers(
        @Query("q", encoded = true) query: String,
        @Query("per_page")perPage: Int,
        @Query("page")page: Int
    ): Single<SearchResponse>

    @GET("/users/{username}")
    fun getGithubUser(
        @Path("username") userName: String
    ): Single<GetUserResponse>

    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<FollowersResponse>

    @GET("/users/{username}/following")
    fun getFollowing(
        @Path("username") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<FollowersResponse>
}
package com.intija.githubx.models

import androidx.annotation.Keep

@Keep
data class GithubUser(
    val avatar_url: String? = "",
    val bio: String? = "",
    val blog: String? = "",
    val company: String? = "",
    val created_at: String? = "",
    val email: String? = "",
    val events_url: String? = "",
    val followers: Int? = 0,
    val followers_url: String? = "",
    val following: Int? = 0,
    val following_url: String? = "",
    val gists_url: String? = "",
    val gravatar_id: String? = "",
    val hireable: Boolean? = false,
    val html_url: String? = "",
    val id: Int? = 0,
    val location: String? = "",
    val login: String? = "",
    val name: String? = "",
    val node_id: String? = "",
    val organizations_url: String? = "",
    val public_gists: Int? = 0,
    val public_repos: Int? = 0,
    val received_events_url: String? = "",
    val repos_url: String? = "",
    val site_admin: Boolean? = false,
    val starred_url: String? = "",
    val subscriptions_url: String? = "",
    val twitter_username: String? = "",
    val type: String? = "",
    val updated_at: String? = "",
    val url: String? = ""
)
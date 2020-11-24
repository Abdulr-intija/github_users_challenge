package com.intija.data.mappers

import com.intija.data.models.user.GetUserResponse
import com.intija.domain.entities.GithubUserEntity

class GithubUserMapper: DataToDomainMapper<GetUserResponse, GithubUserEntity> {
    override fun mapFromDataToDomain(model: GetUserResponse): GithubUserEntity {
        return GithubUserEntity(
            avatar_url = model.avatarUrl ?: "",
            events_url = model.eventsUrl ?: "",
            followers_url = model.followersUrl ?: "",
            following_url = model.followingUrl ?: "",
            gists_url = model.gistsUrl ?: "",
            gravatar_id = model.gravatarId ?: "",
            html_url = model.htmlUrl ?: "",
            id = model.id ?: 0,
            login = model.login ?: "",
            node_id = model.nodeId ?: "",
            organizations_url = model.organizationsUrl ?: "",
            received_events_url = model.receivedEventsUrl ?: "",
            repos_url = model.reposUrl ?: "",
            site_admin = model.siteAdmin ?: false,
            starred_url = model.starredUrl ?: "",
            subscriptions_url = model.subscriptionsUrl ?: "",
            type = model.type ?: "",
            url = model.url ?: "",
            bio = model.bio ?: "",
            blog = model.blog ?: "",
            company = model.company ?: "",
            created_at = model.createdAt ?: "",
            email = model.email ?: "",
            followers = model.followers ?: 0,
            following = model.following ?: 0,
            hireable = model.hireable ?: false,
            location = model.location ?: "",
            name = model.name ?: "",
            public_gists = model.publicGists ?: 0,
            public_repos = model.publicRepos ?: 0,
            twitter_username = model.twitterUsername ?: "",
            updated_at = model.updatedAt ?: ""
        )
    }
}
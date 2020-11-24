package com.intija.data.mappers

import com.intija.data.models.followers.Follower
import com.intija.domain.entities.FollowerEntity

class FollowsMapper: DataToDomainMapper<Follower, FollowerEntity> {
    override fun mapFromDataToDomain(model: Follower): FollowerEntity {
        return FollowerEntity(
            avatar_url = model.avatarUrl?: "",
            events_url = model.eventsUrl?: "",
            followers_url = model.followersUrl?: "",
            following_url = model.followingUrl?: "",
            gists_url = model.gistsUrl?: "",
            gravatar_id = model.gravatarId?: "",
            html_url = model.htmlUrl?: "",
            id = model.id?: 0,
            login = model.login?: "",
            node_id = model.nodeId?: "",
            organizations_url = model.organizationsUrl?: "",
            received_events_url = model.receivedEventsUrl?: "",
            repos_url = model.reposUrl?: "",
            site_admin = model.siteAdmin?: false,
            starred_url = model.starredUrl?: "",
            subscriptions_url = model.subscriptionsUrl?: "",
            type = model.type?: "",
            url = model.url ?: ""
        )
    }
}
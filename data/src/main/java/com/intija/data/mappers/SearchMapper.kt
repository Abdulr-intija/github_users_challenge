package com.intija.data.mappers

import com.intija.data.models.search.Item
import com.intija.domain.entities.GithubSearchUserEntity

class SearchMapper: DataToDomainMapper<Item, GithubSearchUserEntity> {
    override fun mapFromDataToDomain(model: Item): GithubSearchUserEntity {
        return GithubSearchUserEntity(
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
            score = model.score ?: 0,
            site_admin = model.siteAdmin ?: false,
            starred_url = model.starredUrl ?: "",
            subscriptions_url = model.subscriptionsUrl ?: "",
            type = model.type ?: "",
            url = model.url ?: ""
        )
    }
}
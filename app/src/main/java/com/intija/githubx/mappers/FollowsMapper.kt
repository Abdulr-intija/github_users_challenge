package com.intija.githubx.mappers

import com.intija.domain.entities.FollowerEntity
import com.intija.githubx.models.Follower

class FollowsMapper: DomainToUIMapper<FollowerEntity, Follower> {
    override fun mapDomainToUI(model: FollowerEntity): Follower {
        return Follower(
            avatar_url = model.avatar_url,
            events_url = model.events_url,
            followers_url = model.followers_url,
            following_url = model.following_url,
            gists_url = model.gists_url,
            gravatar_id = model.gravatar_id,
            html_url = model.html_url,
            id = model.id,
            login = model.login,
            node_id = model.node_id,
            organizations_url = model.organizations_url,
            received_events_url = model.received_events_url,
            repos_url = model.repos_url,
            site_admin = model.site_admin,
            starred_url = model.starred_url,
            subscriptions_url = model.subscriptions_url,
            type = model.type,
            url = model.url
        )
    }
}
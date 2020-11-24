package com.intija.githubx.mappers

import com.intija.domain.entities.GithubSearchUserEntity
import com.intija.githubx.models.GithubSearchUser

class SearchMapper: DomainToUIMapper<GithubSearchUserEntity, GithubSearchUser> {
    override fun mapDomainToUI(model: GithubSearchUserEntity): GithubSearchUser {
        return GithubSearchUser(
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
            score = model.score,
            site_admin = model.site_admin,
            starred_url = model.starred_url,
            subscriptions_url = model.subscriptions_url,
            type = model.type,
            url = model.url
        )
    }
}
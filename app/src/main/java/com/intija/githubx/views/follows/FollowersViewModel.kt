package com.intija.githubx.views.follows

import androidx.lifecycle.MutableLiveData
import com.intija.domain.usecases.GetFollowersUseCase
import com.intija.githubx.mappers.FollowsMapper
import com.intija.githubx.models.Follower
import com.intija.githubx.utils.ViewState
import com.intija.githubx.viewmodel.BaseSchedulerProvider
import com.intija.githubx.viewmodel.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class FollowersViewModel @Inject constructor(
    private val followersUseCase: GetFollowersUseCase,
    private val followersMapper: FollowsMapper,
    baseSchedulerProvider: BaseSchedulerProvider
): BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {
    val followers = MutableLiveData<ViewState<List<Follower>>>()

    fun getFollowers(userName: String, page: Int){
        execute(
            loadingConsumer = Consumer {
                followers.postValue(ViewState.loading())
            },
            throwableConsumer = Consumer {
                followers.postValue(ViewState.error(it.message))
            },
            successConsumer = Consumer {
                followers.postValue(ViewState.success(it.map { follower ->
                    followersMapper.mapDomainToUI(follower)
                }))
            },
            useCase = followersUseCase.getFollowers(userName, page)
        )

    }

}
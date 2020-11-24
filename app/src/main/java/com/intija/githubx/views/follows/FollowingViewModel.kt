package com.intija.githubx.views.follows

import androidx.lifecycle.MutableLiveData
import com.intija.domain.usecases.GetFollowingUseCase
import com.intija.githubx.mappers.FollowsMapper
import com.intija.githubx.models.Follower
import com.intija.githubx.utils.ViewState
import com.intija.githubx.viewmodel.BaseSchedulerProvider
import com.intija.githubx.viewmodel.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class FollowingViewModel @Inject constructor(
    private val followingsUseCase: GetFollowingUseCase,
    private val followingsMapper: FollowsMapper,
    baseSchedulerProvider: BaseSchedulerProvider
): BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {
    val followings = MutableLiveData<ViewState<List<Follower>>>()

    fun getFollowing(userName: String, page: Int){
        execute(
            loadingConsumer = Consumer {
                followings.postValue(ViewState.loading())
            },
            throwableConsumer = Consumer {
                followings.postValue(ViewState.error(it.message))
            },
            successConsumer = Consumer {
                followings.postValue(ViewState.success(it.map { following ->
                    followingsMapper.mapDomainToUI(following)
                }))
            },
            useCase = followingsUseCase.getFollowing(userName, page)
        )

    }

}
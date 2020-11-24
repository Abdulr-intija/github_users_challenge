package com.intija.githubx.views.githubuser

import androidx.lifecycle.MutableLiveData
import com.intija.domain.usecases.GetGithubUserUseCase
import com.intija.githubx.mappers.GithubUserMapper
import com.intija.githubx.models.GithubUser
import com.intija.githubx.utils.ViewState
import com.intija.githubx.viewmodel.BaseSchedulerProvider
import com.intija.githubx.viewmodel.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class GithubUserViewModel @Inject constructor(
    private val githubUserUseCase: GetGithubUserUseCase,
    private val githubUserMapper: GithubUserMapper,
    baseSchedulerProvider: BaseSchedulerProvider
): BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {
    val result = MutableLiveData<ViewState<GithubUser>>()

    fun getGithubUser(githubUserName: String){
        execute(
            loadingConsumer = Consumer {
                result.postValue(ViewState.loading())
            },
            throwableConsumer = Consumer {
                result.postValue(ViewState.error(it.message))
            },
            successConsumer = Consumer {
                result.postValue(ViewState.success(githubUserMapper.mapDomainToUI(it)))
            },
            useCase = githubUserUseCase.getGithubUser(githubUserName)
        )

    }

}
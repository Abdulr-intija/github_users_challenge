package com.intija.githubx.views.search

import androidx.lifecycle.MutableLiveData
import com.intija.domain.usecases.SearchGithubUsersUseCase
import com.intija.githubx.mappers.SearchMapper
import com.intija.githubx.models.GithubSearchUser
import com.intija.githubx.utils.ViewState
import com.intija.githubx.viewmodel.BaseSchedulerProvider
import com.intija.githubx.viewmodel.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchGithubUsersUseCase: SearchGithubUsersUseCase,
    private val searchMapper: SearchMapper,
    baseSchedulerProvider: BaseSchedulerProvider
): BaseViewModel(baseSchedulerProvider = baseSchedulerProvider) {
    val searchResults = MutableLiveData<ViewState<List<GithubSearchUser>>>()

    fun searchUsers(query: String){
        execute(
            loadingConsumer = Consumer {
                searchResults.postValue(ViewState.loading())
            },
            throwableConsumer = Consumer {
                searchResults.postValue(ViewState.error(it.message))
            },
            successConsumer = Consumer {
                searchResults.postValue(ViewState.success(it.map { searchUser ->
                    searchMapper.mapDomainToUI(searchUser)
                }))
            },
            useCase = searchGithubUsersUseCase.searchGithubUsers(query)
        )

    }

}
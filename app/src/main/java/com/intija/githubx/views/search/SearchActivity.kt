package com.intija.githubx.views.search

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.intija.githubx.R
import com.intija.githubx.models.GithubSearchUser
import com.intija.githubx.utils.ViewState
import com.intija.githubx.views.githubuser.GithubUser
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search_screen.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchResultsAdapter: SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)

        initViewModel()
        setupSearchResultsRecyclerView()
        listenForUserSearchesQueries()
        listenForSearchResultItemClicks()
    }

    private fun listenForSearchResultItemClicks() {
        searchResultsAdapter.itemClickData.observe(this, Observer {
            //Using ActivityOptions to enable transition animation of thumb image between activities
            val animationOptions = ActivityOptions
                .makeSceneTransitionAnimation(this, it["view"] as ImageView, "thumb")
            val intent = Intent(this, GithubUser::class.java)
                .putExtra("githubUserName", it["githubUserName"].toString())

            startActivity(intent, animationOptions.toBundle())
        })
    }

    private fun listenForUserSearchesQueries() {
        //setting up keyboard 'GO' action characteristics
        searchBox.setImeActionLabel("GO", EditorInfo.IME_ACTION_GO)

        searchBox.setOnEditorActionListener { _, actionID, _ ->
            if(actionID == EditorInfo.IME_ACTION_GO){
                observeForSearchResults(searchBox.text.toString())
            }
            false
        }
    }

    private fun observeForSearchResults(searchQuery: String) {
        searchViewModel.searchResults.observe(this, Observer {
            when (it.status){
                ViewState.Status.LOADING -> {
                    showProgressBar(true)
                }

                ViewState.Status.ERROR -> {
                    //empty results adapter when search returns an error
                    updateUI(listOf())
                    showProgressBar(false)
                    Snackbar.make(recyclerView, getString(R.string.error_occurred), Snackbar.LENGTH_LONG).show()
                }

                ViewState.Status.SUCCESS -> {
                    showProgressBar(false)
                    updateUI(it.data)
                }
            }
        })

        searchViewModel.searchUsers(searchQuery)
    }

    private fun setupSearchResultsRecyclerView() {
        searchResultsAdapter = SearchResultsAdapter()

        recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = searchResultsAdapter
        }
    }

    private fun updateUI(searchResults: List<GithubSearchUser>?) {
        //update result counter text view
        val totalCards = searchResults?.size
        resultCount.text = String.format("%d %s found", totalCards, if(totalCards == 1) "user" else "users")

        //Update search results view
        searchResultsAdapter.updateAdapter(searchResults!!)
    }

    private fun showProgressBar(show: Boolean) {
        if(show){
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            //take off content placeholder image
            placeholderImage.visibility = View.GONE
        }else{
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }
}

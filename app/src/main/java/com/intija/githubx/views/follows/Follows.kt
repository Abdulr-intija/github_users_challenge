package com.intija.githubx.views.follows

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intija.githubx.R
import com.intija.githubx.models.Follower
import com.intija.githubx.utils.ViewState
import com.intija.githubx.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_follows.*
import javax.inject.Inject

class Follows : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var followsViewModel: BaseViewModel
    private lateinit var followsAdapter: FollowsAdapter

    private var isFollowers: Boolean = false
    private var page: Int = 1
    private lateinit var githubUserName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follows)

        receiveIntentData()
        initViewModel()
        setUpActionBar()
        setupRecyclerView()
        whenScrollHitsBottom()

        if(isFollowers) observeFollowers() else observeFollowings()

        if(isFollowers) fetchFollowers() else fetchFollowings()
    }

    private fun setUpActionBar() {
        title = if(isFollowers) "Followers" else "Following"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun whenScrollHitsBottom() {
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE){
                    //indicate load more
                    page++

                    if(isFollowers) fetchFollowers() else fetchFollowings()
                }
            }
        })
    }

    private fun fetchFollowers() {
        (followsViewModel as FollowersViewModel).getFollowers(githubUserName, page)
    }

    private fun fetchFollowings() {
        (followsViewModel as FollowingViewModel).getFollowing(githubUserName, page)
    }

    private fun observeFollowings() {
        (followsViewModel as FollowingViewModel).followings.observe(this, Observer {
        updateUIfromViewState(it)
        })
    }

    private fun updateUIfromViewState(viewState: ViewState<List<Follower>>?) {
        when(viewState?.status){
            ViewState.Status.LOADING -> {
                showProgress(true)
            }

            ViewState.Status.ERROR -> {
                showProgress(false)
            }

            ViewState.Status.SUCCESS -> {
                showProgress(false)
                updateAdapter(viewState.data)
            }
        }
    }

    private fun updateAdapter(data: List<Follower>?) {
        followsAdapter.updateAdapter(data!!)
    }

    private fun observeFollowers() {
        (followsViewModel as FollowersViewModel).followers.observe(this, Observer {
            updateUIfromViewState(it)
        })
    }

    private fun setupRecyclerView() {
        followsAdapter = FollowsAdapter()

        recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = followsAdapter
        }
    }

    private fun receiveIntentData() {
        if(!intent?.hasExtra("isFollowers")!! || !intent.hasExtra("githubUserName")){
            finish()
            return
        }

        githubUserName = intent?.getStringExtra("githubUserName")!!
        isFollowers = intent?.getBooleanExtra("isFollowers", false)!!
    }

    private fun initViewModel() {
        val viewModelType = if(isFollowers) FollowersViewModel::class.java else FollowingViewModel::class.java

        followsViewModel = ViewModelProvider(this, viewModelFactory).get(viewModelType)
    }

    private fun showProgress(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // back arrow click
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
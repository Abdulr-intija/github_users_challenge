package com.intija.githubx.views.githubuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.intija.githubx.R
import com.intija.githubx.databinding.ActivityGithubUserBinding
import com.intija.githubx.models.GithubUser
import com.intija.githubx.utils.ViewState
import com.intija.githubx.views.follows.Follows
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_github_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubUser : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var githubUserViewModel: GithubUserViewModel
    private lateinit var githubUserName: String
    private lateinit var dataBindingUtil: ActivityGithubUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBindingUtil = DataBindingUtil.setContentView(this, R.layout.activity_github_user)

        initViewModel()
        retrieveGithubUserName()
        observeGithubUserDetails()
    }

    private fun initViewModel() {
        githubUserViewModel = ViewModelProvider(this, viewModelFactory).get(GithubUserViewModel::class.java)
    }

    private fun observeGithubUserDetails() {
        githubUserViewModel.result.observe(this, Observer {
            when(it.status){
                ViewState.Status.LOADING -> {
                    showProgress(true)
                }

                ViewState.Status.ERROR -> {
                    showProgress(false)
                    Log.d("rrrrrr", it?.message.toString())
                    Snackbar.make(progressBar, getString(R.string.error_occurred), Snackbar.LENGTH_LONG).show()
                }

                ViewState.Status.SUCCESS -> {
                    showProgress(false)
                    updateUIWithData(it.data!!)
                }
            }
        })

        githubUserViewModel.getGithubUser(githubUserName)
    }

    private fun showProgress(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

    private fun retrieveGithubUserName() {
        if(!intent?.hasExtra("githubUserName")!!){
            finish()
            return
        }

        githubUserName = intent?.getStringExtra("githubUserName")!!
    }

    private fun updateUIWithData(githubUserDetails: GithubUser) {
        dataBindingUtil.githubUser = githubUserDetails


        /**load avatar into ImageView.
         *
         * Coroutine is used here for procedural execution of each lines in the block.
         * This is to ensure that the target ImageView dimensions are retrieved before passing to Picasso**/

        CoroutineScope(Dispatchers.Main).launch {
            val thumbWidth = thumb.width
            val thumbHeight = thumb.height
            val backgroundThumbWidth = fadedBackgroundThumb.width
            val backgroundThumbHeight = fadedBackgroundThumb.height

            Picasso.get().load(githubUserDetails.avatar_url).resize(thumbWidth, thumbHeight).centerCrop().into(thumb)
            Picasso.get().load(githubUserDetails.avatar_url).resize(backgroundThumbWidth, backgroundThumbHeight).centerCrop().into(fadedBackgroundThumb)
        }
    }

    fun viewFollows(view: View) {
        var intent = Intent(this, Follows::class.java)
            .putExtra("githubUserName", githubUserName)

        when(view.id){
            R.id.followersClicker -> {
                intent.putExtra("isFollowers", true)
            }

            R.id.followingClicker -> {
                intent.putExtra("isFollowers", false)
            }
        }

        startActivity(intent)
    }
}
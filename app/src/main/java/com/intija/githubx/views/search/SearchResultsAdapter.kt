package com.intija.githubx.views.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.intija.githubx.R
import com.intija.githubx.databinding.SearchItemBinding
import com.intija.githubx.models.GithubSearchUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResultsAdapter: RecyclerView.Adapter<SearchResultsAdapter.CardsViewHolder>() {
    private var searchResults: List<GithubSearchUser> = mutableListOf()
    val itemClickData: MutableLiveData<Map<String, Any?>> = MutableLiveData()

    fun updateAdapter(searchResults: List<GithubSearchUser>){
        this.searchResults = searchResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_item,
            parent,
            false
        )
    )

    override fun getItemCount() = searchResults.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.searchItemBinding.githubUser = searchResults[position]

        val thumbView: ImageView = holder.itemView.thumb as ImageView

        /** Using coroutine to ensure the thumb ImageView width and height is retrieved before invoking Picasso to avoid exceptions **/
        CoroutineScope(Dispatchers.Main).launch {
            val thumbWidth = thumbView.width
            val thumbHeight = thumbView.height

            Picasso.get().load(searchResults[position].avatar_url)
                .resize(thumbWidth, thumbHeight).centerCrop().into(thumbView)
        }

        //On click, notify the search activity observer with the Github user data and thumb ImageView reference
        holder.itemView.itemClicker.setOnClickListener {
            itemClickData.value = mapOf("githubUserName" to searchResults[position].login, "view" to thumbView)
        }

    }

    inner class CardsViewHolder(
        val searchItemBinding: SearchItemBinding
    ): RecyclerView.ViewHolder(searchItemBinding.root)
}
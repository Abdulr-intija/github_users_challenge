package com.intija.githubx.views.follows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.intija.githubx.R
import com.intija.githubx.databinding.FollowItemBinding
import com.intija.githubx.models.Follower
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowsAdapter: RecyclerView.Adapter<FollowsAdapter.CardsViewHolder>() {
    private var follows: List<Follower> = mutableListOf()

    fun updateAdapter(follows: List<Follower>){
        this.follows += follows
        notifyItemInserted(this.follows.size - follows.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.follow_item,
            parent,
            false
        )
    )

    override fun getItemCount() = follows.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.followItemBinding.followItem = follows[position]

        val thumbView: ImageView = holder.itemView.thumb as ImageView

        /** Using coroutine to ensure the thumb ImageView width and height is retrieved before invoking Picasso to avoid exceptions **/
        CoroutineScope(Dispatchers.Main).launch {
            val thumbWidth = thumbView.width
            val thumbHeight = thumbView.height

            Picasso.get().load(follows[position].avatar_url)
                .resize(thumbWidth, thumbHeight).centerCrop().into(thumbView)
        }

    }

    inner class CardsViewHolder(
        val followItemBinding: FollowItemBinding
    ): RecyclerView.ViewHolder(followItemBinding.root)
}
package com.intija.data.models.search


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)
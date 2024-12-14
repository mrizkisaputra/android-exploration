package com.mrizkisaputra.data.response

import com.google.gson.annotations.SerializedName

data class CustomerReviewItem(
    @field: SerializedName("name")
    val name: String,

    @field: SerializedName("review")
    val review: String,

    @field: SerializedName("date")
    val date: String
)

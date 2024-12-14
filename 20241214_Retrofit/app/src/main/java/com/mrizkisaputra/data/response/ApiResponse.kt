package com.mrizkisaputra.data.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @field: SerializedName("error")
    val error: Boolean,

    @field: SerializedName("message")
    val message: String,

    @field: SerializedName("restaurant")
    val restaurant: Restaurant
)

data class ReviewResponse(
    @field: SerializedName("error")
    val error: String,

    @field: SerializedName("message")
    val message: String,

    @field: SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewItem>
)
package com.mrizkisaputra.data.response

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @field: SerializedName("id")
    val id: String,

    @field: SerializedName("name")
    val name: String,

    @field: SerializedName("description")
    val description: String,

    @field: SerializedName("city")
    val city: String,

    @field: SerializedName("address")
    val address: String,

    @field: SerializedName("pictureId")
    val pictureId: String,

    @field: SerializedName("rating")
    val rating: Float,

    @field: SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewItem>
)

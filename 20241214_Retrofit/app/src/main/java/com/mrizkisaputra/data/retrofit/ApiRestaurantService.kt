package com.mrizkisaputra.data.retrofit

import com.mrizkisaputra.data.response.RestaurantResponse
import com.mrizkisaputra.data.response.ReviewRequest
import com.mrizkisaputra.data.response.ReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRestaurantService {

    @GET("/detail/{restaurant_id}")
    fun getRestaurant(@Path("restaurant_id") id: String): Call<RestaurantResponse>

    @Headers("Content-Type: application/json")
    @POST("/review")
    fun createReview(@Body review: ReviewRequest): Call<ReviewResponse>

}
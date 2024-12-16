package com.mrizkisaputra.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrizkisaputra.data.response.CustomerReviewItem
import com.mrizkisaputra.data.response.Restaurant
import com.mrizkisaputra.data.response.RestaurantResponse
import com.mrizkisaputra.data.response.ReviewRequest
import com.mrizkisaputra.data.response.ReviewResponse
import com.mrizkisaputra.data.retrofit.ApiConfig
import com.mrizkisaputra.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewmodel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
        private set
        get() = field

    var restaurant = MutableLiveData<Restaurant>()
        private set
        get() = field

    var customerReviews = MutableLiveData<List<CustomerReviewItem>>()
        private set
        get() = field

    var snackbarText = MutableLiveData<Event<String>>()
        private set
        get() = field

    init {
        findRestaurant()
    }

    private fun findRestaurant() {
        isLoading.value = true
        val client = ApiConfig.getApiRestaurantService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                isLoading.value = false
                if (response.isSuccessful) {
                    val body: RestaurantResponse = response.body() as RestaurantResponse
                    restaurant.value = body.restaurant
                    customerReviews.value = body.restaurant.customerReviews
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, error: Throwable) {
                isLoading.value = false
                Log.e(TAG, "onFailure: ${error.message}")
            }

        })
    }

    fun createReview(review: String) {
        isLoading.value = true
        val reviewRequest = ReviewRequest(RESTAURANT_ID, "mrizkisaputra", review)
        ApiConfig.getApiRestaurantService().createReview(reviewRequest)
            .enqueue(object : Callback<ReviewResponse> {
                override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                    isLoading.value = false
                    val responseBody: ReviewResponse? = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        customerReviews.value = responseBody.customerReviews
                        snackbarText.value = Event(responseBody.message)
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(p0: Call<ReviewResponse>, error: Throwable) {
                    isLoading.value = false
                    Log.e(TAG, "onFailure: ${error.message}")
                }

            })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

}
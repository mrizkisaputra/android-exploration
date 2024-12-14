package com.mrizkisaputra.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mrizkisaputra.R
import com.mrizkisaputra.data.response.CustomerReviewItem
import com.mrizkisaputra.data.response.Restaurant
import com.mrizkisaputra.data.response.RestaurantResponse
import com.mrizkisaputra.data.response.ReviewRequest
import com.mrizkisaputra.data.response.ReviewResponse
import com.mrizkisaputra.data.retrofit.ApiConfig
import com.mrizkisaputra.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val layoutManager = LinearLayoutManager(this)
        binding.recyclerReview.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerReview.addItemDecoration(itemDecoration)

        findRestaurant()
        binding.buttonSend.setOnClickListener {
            createReview(binding.edittextReview.text.toString().trim())
        }
    }

    private fun createReview(review: String) {
        val reviewRequest = ReviewRequest(RESTAURANT_ID, "mrizkisaputra", review)
        ApiConfig.getApiRestaurantService().createReview(reviewRequest)
            .enqueue(object : Callback<ReviewResponse> {
                override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                    val responseBody: ReviewResponse? = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        setReviewCustomers(responseBody.customerReviews)
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(p0: Call<ReviewResponse>, error: Throwable) {
                    Log.e(TAG, "onFailure: ${error.message}")
                }

            })
    }

    private fun findRestaurant() {
        showLoading(true)
        val client = ApiConfig.getApiRestaurantService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val body: RestaurantResponse = response.body() as RestaurantResponse
                    setDataRestaurant(body.restaurant)
                    setReviewCustomers(body.restaurant.customerReviews)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, error: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${error.message}")
            }

        })
    }

    private fun setReviewCustomers(customerReviews: List<CustomerReviewItem>) {
        val adapter = ReviewAdapter()
        adapter.submitList(customerReviews)
        binding.recyclerReview.adapter = adapter
    }

    private fun setDataRestaurant(restaurant: Restaurant) {
        binding.textTitle.text = restaurant.name
        binding.textDescription.text = restaurant.description
        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.imageviewPicture)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}
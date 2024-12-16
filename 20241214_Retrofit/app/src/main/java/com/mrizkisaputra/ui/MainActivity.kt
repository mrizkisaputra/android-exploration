package com.mrizkisaputra.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
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
    private val mainViewmodel: MainViewmodel by viewModels()

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

        mainViewmodel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        mainViewmodel.restaurant.observe(this) { restaurant ->
            setDataRestaurant(restaurant)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerReview.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerReview.addItemDecoration(itemDecoration)
        mainViewmodel.customerReviews.observe(this) { customerReviews ->
            setReviewCustomers(customerReviews)
        }

        mainViewmodel.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { text ->
                Snackbar.make(window.decorView.rootView, text, Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.buttonSend.setOnClickListener { view ->
            mainViewmodel.createReview(binding.edittextReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
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

}
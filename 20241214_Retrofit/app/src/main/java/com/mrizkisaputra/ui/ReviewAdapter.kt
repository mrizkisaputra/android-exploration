package com.mrizkisaputra.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mrizkisaputra.data.response.CustomerReviewItem
import com.mrizkisaputra.databinding.ItemCustomerReviewBinding

class ReviewAdapter : ListAdapter<CustomerReviewItem, ReviewAdapter.ViewHolder>(DIFF_CALLBACK) {


    class ViewHolder(val binding: ItemCustomerReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: CustomerReviewItem){
            binding.textItem.text = "${review.review}\n- ${review.name}"
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomerReviewItem>() {
            override fun areItemsTheSame(oldItem: CustomerReviewItem, newItem: CustomerReviewItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: CustomerReviewItem, newItem: CustomerReviewItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomerReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }
}
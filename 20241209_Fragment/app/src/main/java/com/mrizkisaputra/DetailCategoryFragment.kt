package com.mrizkisaputra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mrizkisaputra.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentDetailCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.getString("EXTRA_NAME") ?: ""
        val categoryDescription = arguments?.getString("EXTRA_DESCRIPTION") ?: ""

        binding.tvCategoryName.text = categoryName
        binding.tvCategoryDescription.text = categoryDescription

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_profile) {

        }
    }
}
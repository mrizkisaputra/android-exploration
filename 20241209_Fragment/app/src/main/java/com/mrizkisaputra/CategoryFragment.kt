package com.mrizkisaputra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.mrizkisaputra.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDetailCategory.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_detail_category) {
            val detailCategory = DetailCategoryFragment()
            detailCategory.arguments = Bundle().apply {
                putString("EXTRA_NAME", "Lifestyle")
                putString("EXTRA_DESCRIPTION", "Lifestyle Description")
            }
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main, detailCategory, DetailCategoryFragment::class.simpleName)
                addToBackStack(null)
            }
        }
    }
}
package com.mrizkisaputra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.mrizkisaputra.databinding.FragmentExampleBinding

class ExampleFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentExampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCategory.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_category) {
            val fragmentCategory = CategoryFragment()
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.main, fragmentCategory, CategoryFragment::class.java.simpleName)
                addToBackStack(null)
            }
        }
    }


}
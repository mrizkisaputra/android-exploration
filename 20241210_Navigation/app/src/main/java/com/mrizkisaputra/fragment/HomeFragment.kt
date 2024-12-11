package com.mrizkisaputra.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.mrizkisaputra.R
import com.mrizkisaputra.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener{
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToSetting.setOnClickListener(this)
        binding.btnToProfile.setOnClickListener(this)
        binding.btnWithAction.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_to_setting -> {
                findNavController().navigate(R.id.action_home_dest_to_setting_dest)
            }
            R.id.btn_to_profile -> {
                v.findNavController().navigate(R.id.action_home_dest_to_profile_dest)
            }
            R.id.btn_with_action -> {
//                val bundle = Bundle().apply {
//                    putString("EXTRA_DATA", "this is data")
//                }
//                findNavController().navigate(R.id.action_home_dest_to_category_dest, bundle)

                val action = HomeFragmentDirections.actionHomeDestToCategoryDest("this is data")
                findNavController().navigate(action)
            }
        }


    }



}
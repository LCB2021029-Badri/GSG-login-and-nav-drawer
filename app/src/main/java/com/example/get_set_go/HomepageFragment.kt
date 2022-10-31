package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.databinding.FragmentHomepageBinding

class HomepageFragment : Fragment() {

    lateinit var binding: FragmentHomepageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(inflater) // view binding instance

//        binding.buttonNextH.setOnClickListener {
//            findNavController().navigate(R.id.action_homepageFragment_to_camFragment)
//        }

        return binding.root
    }
}
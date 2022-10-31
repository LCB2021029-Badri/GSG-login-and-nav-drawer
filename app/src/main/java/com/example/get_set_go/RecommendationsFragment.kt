package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.get_set_go.databinding.FragmentRecommendationsBinding

class RecommendationsFragment : Fragment() {

    lateinit var binding : FragmentRecommendationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendationsBinding.inflate(inflater)



        return binding.root
    }
}
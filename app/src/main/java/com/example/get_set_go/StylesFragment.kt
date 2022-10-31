package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.get_set_go.adapter.ItemAdapter
import com.example.get_set_go.data.Datasource
import com.example.get_set_go.databinding.FragmentStylesBinding

class StylesFragment : Fragment() {

    lateinit var binding : FragmentStylesBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStylesBinding.inflate(inflater)

        return binding.root
    }
}
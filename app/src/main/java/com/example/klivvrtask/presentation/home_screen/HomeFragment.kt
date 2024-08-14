package com.example.klivvrtask.presentation.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klivvrtask.R
import com.example.klivvrtask.domain.model.City
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.launch
import com.example.klivvrtask.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment(), LocationClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: CitiesRecyclerViewAdapter = CitiesRecyclerViewAdapter(emptyList(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getCityList()
        binding.homeSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchByPrefix(newText.orEmpty())
                    return true
                }
            }
        )
    }

    private fun setupRecyclerView() {
        binding.homeRecycler.adapter = adapter
        binding.homeRecycler.setHasFixedSize(true)
        binding.homeRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getCityList() {
        lifecycleScope.launch {
            viewModel.cityList.collect { cities ->
                adapter.updateData(cities)
            }
        }
    }



    override fun locationClicked(city: City) {
      viewModel.showCityLocation(city)
    }


}


package com.example.hyperdesigntask.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hyperdesigntask.databinding.FragmentHomeBinding
import com.example.hyperdesigntask.home.adapter.ShippmentAdapter
import com.example.hyperdesigntask.home.repo.HomeRepoImp
import com.example.hyperdesigntask.home.viewmodel.HomeViewModel
import com.example.hyperdesigntask.home.viewmodel.HomeViewModelFactory
import com.example.hyperdesigntask.network.ApiClient


class HomeFragment : Fragment() {
    private lateinit var  binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    //AvOy2LyaZXetCEeg3lkNEEJIGF4TGk3nb0R8YUvJTU6ioBqLwIRWqKBTj1b9
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelReady()
        homeViewModel.getShipments("AvOy2LyaZXetCEeg3lkNEEJIGF4TGk3nb0R8YUvJTU6ioBqLwIRWqKBTj1b9")
        homeViewModel.shippmentResponse.observe(requireActivity()){
            if(it.isSuccessful) {
                val shippmentAdapter = ShippmentAdapter(it.body()?.shippments?.data)
                binding.rvShippments.adapter = shippmentAdapter
                binding.rvShippments.layoutManager= LinearLayoutManager(requireContext(),
                    RecyclerView.VERTICAL,false)
                shippmentAdapter.setOnClickListener(object : ShippmentAdapter.OnItemClickListener {
                    override fun onItemClicked(id:Int) {

                        val action =   HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)
                        findNavController().navigate(action)
                    }
                })
            }
        }

    }
    private fun getViewModelReady() {
        val factory = HomeViewModelFactory(HomeRepoImp(ApiClient))
        homeViewModel =
            ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]
    }

}
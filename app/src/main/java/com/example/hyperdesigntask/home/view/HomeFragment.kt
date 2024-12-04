package com.example.hyperdesigntask.home.view

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.auth.register.view.RegisterFragment.Companion.KEY
import com.example.hyperdesigntask.databinding.FragmentHomeBinding
import com.example.hyperdesigntask.home.adapter.ShippmentAdapter
import com.example.hyperdesigntask.home.repo.HomeRepoImp
import com.example.hyperdesigntask.home.viewmodel.HomeViewModel
import com.example.hyperdesigntask.home.viewmodel.HomeViewModelFactory
import com.example.hyperdesigntask.network.ApiClient


class HomeFragment : Fragment() {
    private lateinit var  binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tokenSharedPreferences: SharedPreferences

    companion object {
        const val KEY = "token_pref"
    }
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
        tokenSharedPreferences = createSharedPreferences()
        getViewModelReady()
        val token = getToken(tokenSharedPreferences)
        if (token != null) {
            getViewModelReady()
            homeViewModel.getShipments(token)
        }
        homeViewModel.shippmentResponse.observe(requireActivity()){
            if(it.isSuccessful) {
                val shippmentAdapter = ShippmentAdapter(it.body()?.shippments?.data)
                binding.rvShippments.adapter = shippmentAdapter
                binding.rvShippments.layoutManager= LinearLayoutManager(requireContext(),
                    RecyclerView.VERTICAL,false)
                shippmentAdapter.setOnClickListener(object : ShippmentAdapter.OnItemClickListener {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onItemClicked(id:Int) {
                     val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment3(id)
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

    private fun createSharedPreferences(): SharedPreferences {
        return requireContext().getSharedPreferences(KEY, MODE_PRIVATE)
    }

    private fun getToken(sharedPreferences: SharedPreferences): String? {
        return sharedPreferences.getString("token", null)
    }

}
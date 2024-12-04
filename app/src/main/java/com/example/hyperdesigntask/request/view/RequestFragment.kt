package com.example.hyperdesigntask.request.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.databinding.FragmentDetailsBinding
import com.example.hyperdesigntask.databinding.FragmentRequestBinding
import com.example.hyperdesigntask.home.repo.HomeRepoImp
import com.example.hyperdesigntask.home.viewmodel.HomeViewModel
import com.example.hyperdesigntask.home.viewmodel.HomeViewModelFactory
import com.example.hyperdesigntask.network.ApiClient
import com.example.hyperdesigntask.request.model.RequestContainer
import com.example.hyperdesigntask.request.model.RequestQuotation
import com.example.hyperdesigntask.request.repo.RequestRepoImp
import com.example.hyperdesigntask.request.viewmodel.RequestViewModel
import com.example.hyperdesigntask.request.viewmodel.RequestViewModelFactory

class RequestFragment : Fragment() {
    private lateinit var binding: FragmentRequestBinding
    private lateinit var requestViewModel: RequestViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelReady()
        val requestQuotation = RequestQuotation(
            shipment_name = "name of shipment",
            description = "shipment description",
            quantity = "2",
            containerNumber = listOf(
                RequestContainer(
                    number = "21388uhi jknjkjk",
                    size = "Owner name",
                    weight = "Owner name",
                    dimension = "Owner name"
                ),
                RequestContainer(
                    number = "21388uhi",
                    size = "Owner name",
                    weight = "Owner name",
                    dimension = "Owner name"
                )
            ),
            comment = "comment comment.........")

        requestViewModel.requestQuotation("AvOy2LyaZXetCEeg3lkNEEJIGF4TGk3nb0R8YUvJTU6ioBqLwIRWqKBTj1b9",requestQuotation)
        requestViewModel.requestResponse.observe(requireActivity()){
            Toast.makeText(requireContext(), "${it.body()?.message}", Toast.LENGTH_SHORT).show()
        }

    }
    private fun getViewModelReady() {
        val factory = RequestViewModelFactory(RequestRepoImp(ApiClient))
        requestViewModel =
            ViewModelProvider(requireActivity(), factory)[RequestViewModel::class.java]
    }
}
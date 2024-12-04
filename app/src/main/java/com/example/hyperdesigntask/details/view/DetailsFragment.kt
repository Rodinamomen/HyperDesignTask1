package com.example.hyperdesigntask.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.databinding.FragmentDetailsBinding
import com.example.hyperdesigntask.details.repo.DetailsRepoImp
import com.example.hyperdesigntask.details.viewmodel.DetailsViewModel
import com.example.hyperdesigntask.details.viewmodel.DetailsViewModelFactory
import com.example.hyperdesigntask.home.viewmodel.HomeViewModel
import com.example.hyperdesigntask.network.ApiClient


class DetailsFragment : Fragment() {
    private lateinit var detailViewModel: DetailsViewModel
    private lateinit var binding : FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()
        getViewModelReady()
        detailViewModel.shippmentDetailsResponse.observe(requireActivity()){
            if(it.isSuccessful){
                binding.tvShippmentNameDetails.text = it.body()?.shippmentDetails?.shipment_name.toString()
                binding.tvShippmentDescDetails.text = it.body()?.shippmentDetails?.description.toString()
                binding.tvShippmentStatusDetails.text= it.body()?.shippmentDetails?.status.toString()
                binding.tvShippmentCommentDetails.text = it.body()?.shippmentDetails?.comment.toString()
            }
        }
        detailViewModel.getShippmentDetailsResponse("AvOy2LyaZXetCEeg3lkNEEJIGF4TGk3nb0R8YUvJTU6ioBqLwIRWqKBTj1b9",args.id)
    }
    private fun getViewModelReady() {
        val factory = DetailsViewModelFactory(DetailsRepoImp(ApiClient))
        detailViewModel =
            ViewModelProvider(requireActivity(), factory)[DetailsViewModel::class.java]
    }
}
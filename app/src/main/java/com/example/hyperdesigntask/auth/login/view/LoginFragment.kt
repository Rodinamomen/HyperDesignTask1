package com.example.hyperdesigntask.auth.login.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.auth.login.repo.LoginRepoImp
import com.example.hyperdesigntask.auth.login.viewmodel.LoginViewModel
import com.example.hyperdesigntask.auth.login.viewmodel.LoginViewModelFactory
import com.example.hyperdesigntask.databinding.FragmentLoginBinding
import com.example.hyperdesigntask.network.ApiClient
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelReady()
        loginViewModel.loginResponse.observe(requireActivity()){
            Log.d("trac", "onViewCreated:${it}")
            if(it.body()?.status==200){
                Log.d("package:mine", "onViewCreated:${it.body()?.status}")
                Toast.makeText(requireContext(), "${it.body()?.user?.name}", Toast.LENGTH_SHORT).show()
            }else{
                Log.d("package:mine", "onViewCreated:${it.body()?.status}")
                Toast.makeText(requireContext(), "${it.body()?.user?.name}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnLogin.setOnClickListener {
            if(checkNotEmpty(binding.etPhoneNumberLogin)&& checkNotEmpty(binding.etPassword)){
                Log.d("trac", "onViewCreated:${binding.etPassword.editText?.text} ")
                loginViewModel.loginUser(binding.etPhoneNumberLogin.editText?.text.toString(),binding.etPassword.editText?.text.toString(),"")
            }
        }
        binding.tvSignup.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }
    private fun checkNotEmpty(input: TextInputLayout): Boolean {
        if (input.editText?.text.toString().isEmpty()) {
            input.error = "This field is required."
            return false
        } else {

            input.error = null
            return true
        }
    }
    private fun getViewModelReady() {
        val factory = LoginViewModelFactory(LoginRepoImp(ApiClient))
        loginViewModel =
            ViewModelProvider(requireActivity(), factory)[LoginViewModel::class.java]
    }
}
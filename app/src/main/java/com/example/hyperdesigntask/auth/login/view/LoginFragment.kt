package com.example.hyperdesigntask.auth.login.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
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
import com.example.hyperdesigntask.auth.register.view.RegisterFragment.Companion.KEY
import com.example.hyperdesigntask.databinding.FragmentLoginBinding
import com.example.hyperdesigntask.network.ApiClient
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var tokenSharedPreferences: SharedPreferences

    companion object {
        const val KEY = "token_pref"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenSharedPreferences = createSharedPreferences()
        getViewModelReady()
        loginViewModel.loginResponse.observe(requireActivity()) {
            Log.d("trac", "onViewCreated:${it}")
            if (it.body()?.message == "success") {
                Log.d("package:mine", "onViewCreated:${it.body()?.status}")
                val token = it.body()?.access_token
                if (token != null) {
                    setToken(tokenSharedPreferences, token)
                }
                Toast.makeText(requireContext(), "Welcome ${it.body()?.user?.name}", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                Log.d("package:mine", "onViewCreated:${it.body()?.status}")
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
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

    private fun createSharedPreferences(): SharedPreferences {
        return requireContext().getSharedPreferences(KEY, MODE_PRIVATE)
    }

    private fun setToken(sharedPreferences: SharedPreferences, token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token).apply()
    }
}
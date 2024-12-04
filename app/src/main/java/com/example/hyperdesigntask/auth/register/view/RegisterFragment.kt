package com.example.hyperdesigntask.auth.register.view

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.auth.register.repo.RegisterRepoImp
import com.example.hyperdesigntask.auth.register.viewmodel.RegisterViewModel
import com.example.hyperdesigntask.auth.register.viewmodel.RegisterViewModelFactory
import com.example.hyperdesigntask.databinding.FragmentRegisterBinding
import com.example.hyperdesigntask.network.ApiClient
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Multipart
import java.io.File
import java.io.FileNotFoundException
import java.net.URI


class RegisterFragment : Fragment() {
    companion object {
        private const val PICK_IMAGE_REQUEST = 100
        val KEY = "token_pref"
    }
    private lateinit var tokenSharedPreferences: SharedPreferences
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var imageUri: Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModelReady()
        tokenSharedPreferences =createSharedPreferences()
        registerViewModel.registerResponse.observe(requireActivity()){
            if(it.isSuccessful){
                if(it.body()?.status==200){
                    Toast.makeText(requireContext(), "${it.body()?.user?.name}", Toast.LENGTH_SHORT).show()
                        setToken(tokenSharedPreferences, it.body()!!.access_token)
                      setId(tokenSharedPreferences,it.body()!!.user.id.toString())
                    val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            }
            Toast.makeText(requireContext(), "${it.body()?.user?.name}", Toast.LENGTH_SHORT).show()
        }
        imageUri= Uri.parse(" ")
        binding.ivAccount.setOnClickListener {
            val pickImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(pickImage, PICK_IMAGE_REQUEST)
        }
        binding.btnRegister.setOnClickListener {
            if (checkNotEmpty(binding.etName) && checkNotEmpty(binding.etEmail) && checkNotEmpty(
                    binding.etPassword
                ) && checkNotEmpty(binding.etPhoneNumber)
            ) {
                val name = binding.etName.editText?.text.toString()
                val email = binding.etEmail.editText?.text.toString()
                val password = binding.etPassword.editText?.text.toString()
                val phone = binding.etPhoneNumber.editText?.text.toString()
                val countryId = binding.counteryCodePicker.selectedCountryCode.toString()
                val imageFile = getFileFromUri(imageUri)
                if (imageFile != null) {
                    val imageType = requireContext().contentResolver.getType(imageUri) ?: "image/jpeg"
                    val requestBody = imageFile.asRequestBody(imageType.toMediaTypeOrNull())
                    val filePart = MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
                    registerViewModel.registerUser(
                        name,
                        email,
                        phone,
                        password,
                        countryId,
                        "employee",
                        filePart,
                        ""
                    )
                } else {
                    Toast.makeText(requireContext(), "Failed to get image file", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.tvLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            imageUri= data?.data!!
            imageUri.let {
                binding.ivAccount.setImageURI(it)
            }
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
            val factory = RegisterViewModelFactory(RegisterRepoImp(ApiClient))
            registerViewModel =
                ViewModelProvider(requireActivity(), factory)[RegisterViewModel::class.java]
        }
   private fun getFileFromUri(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = cursor?.getString(columnIndex ?: 0)
        cursor?.close()
        return if (filePath != null) File(filePath) else null
    }
    private fun createSharedPreferences(): SharedPreferences{
        val sharedPreferences = requireContext().getSharedPreferences(KEY, MODE_PRIVATE)
        return sharedPreferences
    }
    private  fun setToken(sharedPreferences: SharedPreferences, token:String){
        val editor = sharedPreferences.edit()
        editor.putString("token", token).apply()
    }
    private fun setId(sharedPreferences: SharedPreferences, id:String){
        val editor = sharedPreferences.edit()
        editor.putString("id", id).apply()
    }
    }
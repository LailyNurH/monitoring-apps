package com.night.monitoring.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.night.monitoring.MainActivity
import com.night.monitoring.R
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentRegisterBinding
import com.night.monitoring.model.LoginResponse
import com.night.monitoring.model.register.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val api by lazy { BaseRetrofit().endpoint }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            submitRegister.setOnClickListener {

                Toast.makeText(requireContext(), "Loading... ", Toast.LENGTH_SHORT).show()
                val email = etEmail.text.toString()
                val nohp = etNohp.text.toString()
                val password = etPassword.text.toString()
                val nama = etName.text.toString()

                api.register(email,password,nama,nohp,0).enqueue(object :
                    Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        val correct = response.body()!!.success
                        if (correct) {


                            Toast.makeText(requireContext(), "Password Benar", Toast.LENGTH_LONG)
                                .show()
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            Toast.makeText(requireContext(), "Password salah", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Log.e("LoginError", t.toString())
                    }

                })


            }
        }
    }


}
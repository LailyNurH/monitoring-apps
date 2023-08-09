package com.night.monitoring.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.night.monitoring.MainActivity
import com.night.monitoring.R
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentLoginBinding
import com.night.monitoring.model.LoginResponse
import com.night.monitoring.utils.SessionManager
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private val api by lazy { BaseRetrofit().endpoint }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SignInActivity.sessionManager = SessionManager(requireContext())

        val loginStatus = SignInActivity.sessionManager.getBoolean("LOGIN_STATUS")
//        if (loginStatus) {
//            val moveIntent = Intent(this@SignInActivity, MainActivity::class.java)
//            startActivity(moveIntent)
//            finish()
//        }

        with(binding) {


        binding.btnSignIn.setOnClickListener {
            Toast.makeText(requireContext(), "Proses Login", Toast.LENGTH_SHORT).show()

            api.login(binding.etEmail.text.toString(), binding.etPassword.text.toString()).enqueue(object :
                Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val correct = response.body()!!.success
                    if (correct) {
                        //untuk menyimpan session dengan mendapatkan data token dari response body
                        val token = response.body()!!.data.token
                        SignInActivity.sessionManager.saveString("TOKEN", "Bearer " + token)
                        SignInActivity.sessionManager.saveBoolean("LOGIN_STATUS", true)
                        SignInActivity.sessionManager.saveString(
                            "USER_ID",
                            response.body()!!.data.user.id.toString()
                        )
                        SignInActivity.sessionManager.saveString(
                            "USERNAME",
                            response.body()!!.data.user.nama
                        )
                        val roleId = response.body()!!.data.user.role
                        SignInActivity.sessionManager.saveString("ROLE_ID", roleId)

                        val  username = response.body()!!.data.user.nama
                        NAME = username

                        Toast.makeText(requireContext(), "Password Benar", Toast.LENGTH_LONG)
                            .show()
                        if(roleId  == "1"){

                        }
                        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)

                    } else {
                        Toast.makeText(requireContext(), "Password salah", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("LoginError", t.toString())
                }

            })
        }
        }

    }


    companion object {
        lateinit var sessionManager: SessionManager
        const val LOGIN_SHARED_PREF = "login_shared_pref"
        const val STATUS_LOGIN = "status_login"
        var NAME = ""
        const val EMAIL = "email"
        var USER_ID = "account_id"
        const val TOKEN = ""
    }
}
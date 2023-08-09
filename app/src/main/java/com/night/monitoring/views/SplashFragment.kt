package com.night.monitoring.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.night.monitoring.R
import com.night.monitoring.utils.SessionManager
import com.night.monitoring.views.LoginFragment.Companion.LOGIN_SHARED_PREF
import com.night.monitoring.views.LoginFragment.Companion.STATUS_LOGIN
import com.night.monitoring.views.LoginFragment.Companion.TOKEN
import com.night.monitoring.views.LoginFragment.Companion.sessionManager


class SplashFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        sharedPreferences = requireContext().getSharedPreferences(
            LOGIN_SHARED_PREF,
            Context.MODE_PRIVATE
        )
        sessionManager = SessionManager(requireContext())
        val roleId = sessionManager.getString("ROLE_ID")
        val loginStatus = sessionManager.getBoolean("LOGIN_STATUS")
        if (loginStatus) {
            if (roleId == "1") {
                goToTeknisiFragment()
            } else {
                goToHome()
            }
        } else {
            goToLogin()
        }

        return view
    }

    private fun goToTeknisiFragment() {
        val postDelayed = Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_teknisiFragment)
        }, 1500)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (sharedPreferences.getString(TOKEN, "") != null) {
            SAVED_TOKEN = sharedPreferences.getString(TOKEN, "").toString()
        }
    }

    private fun isLoginInfoValid(): Boolean {
        return sharedPreferences.getBoolean(STATUS_LOGIN, false)
    }

    private fun goToLogin() {
        val postDelayed = Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 1500)
    }

    private fun goToHome() {
        val postDelayed = Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_menuFragment)
        }, 1500)
    }

    companion object {
        var SAVED_TOKEN = ""
    }

}
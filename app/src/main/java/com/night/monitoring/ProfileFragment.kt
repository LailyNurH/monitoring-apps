package com.night.monitoring

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.night.monitoring.databinding.FragmentProfileBinding
import com.night.monitoring.views.LoginFragment


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name= LoginFragment.sessionManager.getString("USERNAME")
        binding.tvUsername.text = "Hallo " + name
        binding.logout.setOnClickListener {
            LoginFragment.sessionManager.clearSession()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

    }

}
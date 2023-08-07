package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.night.monitoring.R
import com.night.monitoring.databinding.FragmentMenuBinding
import com.night.monitoring.views.LoginFragment.Companion.NAME
import com.night.monitoring.views.LoginFragment.Companion.sessionManager


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val name=sessionManager.getString("USERNAME")
            username.text = "Hallo " + name
            btnMulai.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_paketBerlanggananFragment)
            }
            btnBayar.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_bayarFragment)
            }
            btnRiwayar.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_riwayatFragment)
            }
            btnStatus.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_statusFragment)
            }
            profile.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_profileFragment)
            }

        }

    }


}
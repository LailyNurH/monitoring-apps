package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.night.monitoring.R
import com.night.monitoring.databinding.FragmentKonfrimBayarBinding


class KonfrimBayarFragment : Fragment() {
    private lateinit var binding : FragmentKonfrimBayarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentKonfrimBayarBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}
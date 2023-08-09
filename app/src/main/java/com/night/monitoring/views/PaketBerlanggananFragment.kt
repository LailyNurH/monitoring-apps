package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.night.monitoring.R
import com.night.monitoring.databinding.FragmentPaketBerlanggananBinding


class PaketBerlanggananFragment : Fragment() {
    private lateinit var binding : FragmentPaketBerlanggananBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPaketBerlanggananBinding.inflate(layoutInflater,container,false)
        return binding.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Jenis Berlangganan"


        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.apply {
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
            ktr.setOnClickListener {
                findNavController().navigate(R.id.action_paketBerlanggananFragment_to_membershipFragment)
                TIPE_BERLANGGANAN = "KTR"
            }
            pribadi.setOnClickListener {
                findNavController().navigate(R.id.action_paketBerlanggananFragment_to_membershipFragment)
                TIPE_BERLANGGANAN = "Pribadi"
            }

        }
    }

    companion object{
        var TIPE_BERLANGGANAN = ""
    }

}
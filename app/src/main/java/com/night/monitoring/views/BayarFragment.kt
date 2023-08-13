package com.night.monitoring.views

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.monitoring.R
import com.night.monitoring.adapter.JatuhTempoAdapter
import com.night.monitoring.adapter.MemberAdapter
import com.night.monitoring.adapter.RiwayatPengajuanAdapter.Companion.ID_PENGAJUAN
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentBayarBinding
import com.night.monitoring.model.MemberResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class BayarFragment : Fragment() {
    private lateinit var binding: FragmentBayarBinding
    private val api by lazy { BaseRetrofit().endpoint }
    private lateinit var jatuhTempoAdapter: JatuhTempoAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBayarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
//            pilihtanggal.setOnClickListener {
//                showDatePicker()
//
//            }
//            submit.setOnClickListener {
//                bayar()
//            }

            jatuhTempoAdapter = JatuhTempoAdapter()
            binding.rvJatuhtempo.adapter = jatuhTempoAdapter
            binding.rvJatuhtempo.layoutManager = LinearLayoutManager(requireContext())

            val userid = LoginFragment.sessionManager.getString("USER_ID")
            userid?.toInt()?.let {
                api.jatuh_tempo(userid.toInt())
                    .enqueue(object : Callback<com.night.monitoring.model.member.MemberResponse> {
                        override fun onResponse(
                            call: Call<com.night.monitoring.model.member.MemberResponse>,
                            response: Response<com.night.monitoring.model.member.MemberResponse>
                        ) {
                            if (response.isSuccessful) {
                                val members = response.body()?.data?.membership

                                if (members != null) {
                                    jatuhTempoAdapter.submitList(members)
//                                riwayatAdapter.submitList(response.body()?.data)
                                }
                            } else {
                                binding.llEmptyHistory.visibility = View.VISIBLE
                            }
                        }

                        override fun onFailure(
                            call: Call<com.night.monitoring.model.member.MemberResponse>,
                            t: Throwable
                        ) {
//                            binding.llEmptyHistory.visibility = View.VISIBLE
                        }
                    })
            }
        }
    }
}


//    private fun bayar() {
////        findNavController().navigate(R.id.ba)
//    }
//
//    private fun showDatePicker() {
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        val datePickerDialog = DatePickerDialog(
//            requireContext(),
//            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
//                val selectedDate = formatDate(year, monthOfYear, dayOfMonth)
//                binding.tanggal.setText(selectedDate)
//            },
//            year,
//            month,
//            day
//        )
//
//
//
//        datePickerDialog.show()
//    }
//
//    private fun formatDate(year: Int, month: Int, day: Int): String {
//        // Adjust month to 1-based index (DatePicker gives month from 0 to 11)
//        val formattedMonth = month + 1
//        return "$day/$formattedMonth/$year"
//    }

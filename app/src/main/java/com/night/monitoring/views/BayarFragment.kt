package com.night.monitoring.views

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.night.monitoring.R
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
            pilihtanggal.setOnClickListener {
                showDatePicker()

            }
            submit.setOnClickListener {
                bayar()
            }

            val userid = LoginFragment.sessionManager.getString("USER_ID")
            val idPengajuan = ID_PENGAJUAN.toString().toInt()
            userid?.toInt()?.let {
                api.getDetailMember(idPengajuan).enqueue(object : Callback<MemberResponse> {
                    override fun onResponse(
                        call: Call<com.night.monitoring.model.MemberResponse>,
                        response: Response<MemberResponse>
                    ) {
                        if (response.isSuccessful) {
                            val memberResponse = response.body()
                            idUser.text = memberResponse?.data?.membership?.id
                            totalbauar.text = "Total Pembayaran: ${memberResponse?.data?.membership?.pembayaranMuka}"



                        }
                    }

                    override fun onFailure(call: Call<MemberResponse>, t: Throwable) {
                        // Handle API call failure
                    }
                })
            }
        }
    }

    private fun bayar() {
//        findNavController().navigate(R.id.ba)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = formatDate(year, monthOfYear, dayOfMonth)
                binding.tanggal.setText(selectedDate)
            },
            year,
            month,
            day
        )



        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        // Adjust month to 1-based index (DatePicker gives month from 0 to 11)
        val formattedMonth = month + 1
        return "$day/$formattedMonth/$year"
    }
}
package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.monitoring.adapter.RiwayatPengajuanAdapter
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentRiwayatBinding
import com.night.monitoring.model.member.MemberResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RiwayatFragment : Fragment() {
    private lateinit var binding: FragmentRiwayatBinding
    private lateinit var riwayatAdapter: RiwayatPengajuanAdapter
    private val api by lazy { BaseRetrofit().endpoint }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRiwayatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            riwayatAdapter = RiwayatPengajuanAdapter()
            binding.rvRiwayatlangganan.adapter = riwayatAdapter
            binding.rvRiwayatlangganan.layoutManager = LinearLayoutManager(requireContext())

            val userid = LoginFragment.sessionManager.getString("USER_ID")
            userid?.toInt()?.let {
                api.getPengajuan(it).enqueue(object : Callback<MemberResponse> {
                    override fun onResponse(
                        call: Call<MemberResponse>,
                        response: Response<MemberResponse>
                    ) {
                        if (response.isSuccessful) {
                            val members = response.body()?.data?.membership

                            if (members != null) {
                                riwayatAdapter.submitList(members)
//                                riwayatAdapter.submitList(response.body()?.data)
                            }
                        } else {
                            binding.llEmptyHistory.visibility = View.VISIBLE
                        }
                    }

                    override fun onFailure(call: Call<MemberResponse>, t: Throwable) {
                        // Handle API call failure
                    }
                })
            }
        }
    }


    companion object {
        var TIPE_BERLANGGANAN = ""
    }
}
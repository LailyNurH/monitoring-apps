package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.monitoring.R
import com.night.monitoring.adapter.AktifAdapter
import com.night.monitoring.adapter.MemberAdapter
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentStatusBinding
import com.night.monitoring.model.member.MemberResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatusFragment : Fragment() {
    private lateinit var binding : FragmentStatusBinding
    private lateinit var aktifAdapter: AktifAdapter
    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStatusBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aktifAdapter = AktifAdapter()
        binding.rvPesanan.adapter = aktifAdapter
        binding.rvPesanan.layoutManager = LinearLayoutManager(requireContext())

        val userid = LoginFragment.sessionManager.getString("USER_ID")
        userid?.toInt()?.let {
            api.getStatusYes(userid.toInt()).enqueue(object : Callback<MemberResponse> {
                override fun onResponse(
                    call: Call<MemberResponse>,
                    response: Response<MemberResponse>
                ) {
                    if (response.isSuccessful) {
                        val members = response.body()?.data?.membership

                        if (members != null) {
                            aktifAdapter.submitList(members)
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

package com.night.monitoring.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.night.monitoring.R
import com.night.monitoring.adapter.MemberAdapter
import com.night.monitoring.adapter.RiwayatPengajuanAdapter
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentTeknisiBinding
import com.night.monitoring.model.member.MemberResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Member


class TeknisiFragment : Fragment() {
    private lateinit var binding : FragmentTeknisiBinding

    private lateinit var memberAdapter: MemberAdapter
    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentTeknisiBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            logout.setOnClickListener {
                LoginFragment.sessionManager.clearSession()
                findNavController().navigate(R.id.action_teknisiFragment_to_loginFragment)
            }

            memberAdapter = MemberAdapter()
            binding.rvPemohon.adapter = memberAdapter
            binding.rvPemohon.layoutManager = LinearLayoutManager(requireContext())

            val userid = LoginFragment.sessionManager.getString("USER_ID")
            userid?.toInt()?.let {
                api.showToTeknisi().enqueue(object : Callback<MemberResponse> {
                    override fun onResponse(
                        call: Call<MemberResponse>,
                        response: Response<MemberResponse>
                    ) {
                        if (response.isSuccessful) {
                            val members = response.body()?.data?.membership

                            if (members != null) {
                                memberAdapter.submitList(members)
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


}
package com.night.monitoring.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.night.monitoring.R
import com.night.monitoring.databinding.ItemRiwayatPengajuanBinding
import com.night.monitoring.model.member.Membership

class RiwayatPengajuanAdapter :
    ListAdapter<Membership, RiwayatPengajuanAdapter.KriteriaViewHolder>(Differ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KriteriaViewHolder {
        val binding =
            ItemRiwayatPengajuanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KriteriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KriteriaViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }



    inner class KriteriaViewHolder(
        private val binding: ItemRiwayatPengajuanBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(member: Membership) {
            binding.apply {
                if(member.statusVerifikasi == "N"){
                    cvStatus.setCardBackgroundColor(Color.parseColor("#FBBF24"))
                    tvStatus.text = "Diproses"
                }else{
                    cvStatus.setCardBackgroundColor(Color.parseColor("#22C55E"))
                    tvStatus.text = "Diterima"

                }
                tglPengajuan.text = member.tanggalPengajuan
                tglPemasangan.text = "Tanggal Pemasangan " + "\n"+ member.tglBerlangganan
                Log.d("pemasangan",tglPemasangan.toString())

            }

            binding.root.setOnClickListener {
                ID_PENGAJUAN = member.id
                val navController = itemView.findNavController()
                navController.navigate(R.id.action_riwayatFragment_to_bayarFragment)
            }
        }
    }
    class Differ : DiffUtil.ItemCallback<Membership>() {
        override fun areItemsTheSame(oldItem: Membership, newItem: Membership): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Membership, newItem: Membership): Boolean {
            return oldItem == newItem
        }
    }
    companion object{
        var ID_PENGAJUAN =""
    }
}
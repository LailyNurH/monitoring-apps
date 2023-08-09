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
import com.night.monitoring.databinding.ItemListMemberBinding
import com.night.monitoring.databinding.ItemRiwayatPengajuanBinding
import com.night.monitoring.model.member.Membership

class MemberAdapter :
    ListAdapter<Membership, MemberAdapter.MemberViewHolder>(Differ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding =
            ItemListMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }



    inner class MemberViewHolder(
        private val binding: ItemListMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(member: Membership) {
            binding.apply {

                namaPemohon.text = "Nama Pemohon : " + member.nama
                noHp.text = "Nomor Pemohon : " + member.noHp
                tglPemasangan.text = "Tanggal Pemasangan " + "\n"+ member.tglBerlangganan
                Log.d("pemasangan",tglPemasangan.toString())

            }

            binding.cvStatus.setOnClickListener {
                LOKASI = member.alamat
//                val navController = itemView.findNavController()
//                navController.navigate(R.id.action_riwayatFragment_to_bayarFragment)
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
        var LOKASI =""
    }
}
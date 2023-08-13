package com.night.monitoring.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.night.monitoring.databinding.ItemJatuhtempoBinding
import com.night.monitoring.databinding.ItemListMemberBinding
import com.night.monitoring.model.member.Membership

class JatuhTempoAdapter :
    ListAdapter<Membership, JatuhTempoAdapter.JatuhTempoViewHolder>(Differ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JatuhTempoViewHolder {
        val binding =
            ItemJatuhtempoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JatuhTempoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JatuhTempoViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }



    inner class JatuhTempoViewHolder(
        private val binding: ItemJatuhtempoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(member: Membership) {
            binding.apply {
                pemasang.text = member.nama
                tvJatuhtempo.text = member.jatuh_tempo
//                jumlahunit.text = member.jumlahUnit
                jumlahunit.text = member.jumlahUnit

                val totalBayar = member.jumlahUnit.toInt() * 100000
                totalbauar.text = totalBayar.toString()

                cl4.setOnClickListener {
                    TOTAL_PEMBAYARAN = totalBayar
                    ID_USER = member.idUser.toInt()
                    val navController = itemView.findNavController()
//                navController.navigate(R.id.action_riwayatFragment_to_bayarFragment)
                }

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
        var TOTAL_PEMBAYARAN =0
        var ID_USER =0
    }
}
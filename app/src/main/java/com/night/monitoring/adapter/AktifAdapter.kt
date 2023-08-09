package com.night.monitoring.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.night.monitoring.R
import com.night.monitoring.databinding.ItemAktifBinding
import com.night.monitoring.databinding.ItemListMemberBinding
import com.night.monitoring.model.member.Membership

class AktifAdapter :
    ListAdapter<Membership, AktifAdapter.AktifViewHolder>(Differ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktifViewHolder {
        val binding =
            ItemAktifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AktifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AktifViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }



    inner class AktifViewHolder(
        private val binding: ItemAktifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(member: Membership) {
            binding.apply {

                pemohon.text = member.nama
                totalbayar.text =  member.pembayaranMuka

            }

            binding.root.setOnClickListener {
                AKTIF_ID = member.id
                val navController = itemView.findNavController()
                navController.navigate(R.id.action_statusFragment_to_aktifDetailFragment)
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
        var AKTIF_ID =""
    }
}
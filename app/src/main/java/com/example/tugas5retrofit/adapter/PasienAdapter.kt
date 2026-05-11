package com.example.tugas5retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas5retrofit.R
import com.example.tugas5retrofit.model.Pasien

class PasienAdapter(private val list: List<Pasien>) :
    RecyclerView.Adapter<PasienAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        val tvHp: TextView = itemView.findViewById(R.id.tvHp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pasien, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pasien = list[position]

        holder.tvNama.text = pasien.nama
        holder.tvAlamat.text = pasien.alamat
        holder.tvHp.text = pasien.no_hp
    }
}
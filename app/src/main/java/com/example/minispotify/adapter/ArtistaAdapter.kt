package com.example.minispotify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.OnClickListener
import com.example.minispotify.R
import com.example.minispotify.databinding.ItemArtistaBinding
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity

class ArtistaAdapter(private var artistasEntity: MutableList<ArtistaEntity>, private val listener: OnClickListener):
    RecyclerView.Adapter<ArtistaAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemArtistaBinding.bind(view)

        fun setListener(artistaEntity: ArtistaEntity) {
            binding.root.setOnClickListener{ listener.onClick(artistaEntity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_artista,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artistaEntity = artistasEntity.get(position)

        with(holder){
            setListener(artistaEntity)

            binding.tvAutor.text = artistaEntity.nombre

            Glide.with(context)
                .load(artistaEntity.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgArtista)
        }
    }

    override fun getItemCount(): Int = artistasEntity.size

    fun add(artistaEntity: ArtistaEntity) {
        artistasEntity.add(artistaEntity)
        notifyDataSetChanged()
    }

    fun setArtistas(artistaEntity: MutableList<ArtistaEntity>) {
        artistasEntity = artistaEntity
        notifyDataSetChanged()
    }

    fun update(artistaEntity: ArtistaEntity) {
        val index = artistasEntity.indexOf(artistaEntity)
        if (index != -1) {
            artistasEntity.set(index, artistaEntity)
            notifyItemChanged(index)
        }
    }

    fun delete(artistaEntity: ArtistaEntity){
        val index = artistasEntity.indexOf(artistaEntity)
        if (index != -1) {
            artistasEntity.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}
package com.example.minispotify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.R
import com.example.minispotify.activity.FavSongsActivity
import com.example.minispotify.activity.MainActivity
import com.example.minispotify.databinding.ItemCancionBinding

class CancionAdapter(private var cancionesEntity: MutableList<CancionEntity>, private val listener: MainActivity):
    RecyclerView.Adapter<CancionAdapter.ViewHolder>(){

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemCancionBinding.bind(view)

        fun setListener(cancionEntity: CancionEntity) {
            with(binding){
                root.setOnClickListener{ listener.onClick(cancionEntity) }

                root.setOnLongClickListener {
                    listener.onPauseCancion(cancionEntity)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_cancion,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cancionEntity = cancionesEntity.get(position)

        with(holder){
            setListener(cancionEntity)

            binding.tvAutor.text = cancionEntity.autor
            binding.tvTitulo.text = cancionEntity.titulo

            Glide.with(context)
                .load(cancionEntity.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgCancion)
        }
    }

    override fun getItemCount(): Int = cancionesEntity.size

    fun add(cancionEntity: CancionEntity) {
        cancionesEntity.add(cancionEntity)
        notifyDataSetChanged()
    }

    fun setCanciones(cancionesEntity2: MutableList<CancionEntity>) {
        cancionesEntity = cancionesEntity2
        notifyDataSetChanged()
    }

    fun update(cancionEntity: CancionEntity) {
        val index = cancionesEntity.indexOf(cancionEntity)
        if (index != -1) {
            cancionesEntity.set(index, cancionEntity)
            notifyItemChanged(index)
        }
    }

    fun delete(cancionEntity: CancionEntity){
        val index = cancionesEntity.indexOf(cancionEntity)
        if (index != -1) {
            cancionesEntity.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}
package com.example.minispotify.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.OnClickListener
import com.example.minispotify.R
import com.example.minispotify.activity.NewPlaylistActivity
import com.example.minispotify.databinding.ItemPlaylistBinding
import com.example.minispotify.objetos.PlaylistEntity

class PlaylistAdapter(private var playlistEnity: MutableList<PlaylistEntity>, private val listener: OnClickListener):
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemPlaylistBinding.bind(view)

        fun setListener(playlistEntity: PlaylistEntity) {
            with(binding){
                root.setOnClickListener{ listener.onClick(playlistEntity) }

                root.setOnLongClickListener {
                    listener.onEditPlaylist(playlistEntity)
                    true
                }

                btnDelete.setOnClickListener {
                    delete(playlistEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_playlist,parent, false)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlistEntity = playlistEnity.get(position)

        with(holder){
            setListener(playlistEntity)

            binding.tvNombrePlaylist.text = playlistEntity.titulo

            Glide.with(context)
                .load(playlistEntity.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPlaylist)
        }
    }

    override fun getItemCount(): Int = playlistEnity.size

    fun add(playlistEntity: PlaylistEntity) {
        playlistEnity.add(playlistEntity)
        notifyDataSetChanged()
    }

    fun setPlaylists(playlistEntity: MutableList<PlaylistEntity>) {
        playlistEnity = playlistEntity
        notifyDataSetChanged()
    }

    fun update() {
        notifyDataSetChanged()
    }

    fun updatePlaylist(playlistEntity: PlaylistEntity) {
        val index = playlistEnity.indexOf(playlistEntity)
        if (index != -1) {
            playlistEnity.set(index, playlistEntity)
            notifyItemChanged(index)
        }
    }

    fun delete(playlistEntity: PlaylistEntity){
        val index = playlistEnity.indexOf(playlistEntity)
        if (index != -1) {
            playlistEnity.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}
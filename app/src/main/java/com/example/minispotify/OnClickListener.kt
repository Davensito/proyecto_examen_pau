package com.example.minispotify

import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.PlaylistEntity
import java.text.FieldPosition

interface OnClickListener {
    fun onClick(cancionEntity: CancionEntity)
    fun onClick(artistaEntity: ArtistaEntity)
    fun onClick(playlistEntity: PlaylistEntity)
    fun onPauseCancion(cancionEntity: CancionEntity)
    fun onDeletePlaylist(playlistEntity: PlaylistEntity)
    fun onEditPlaylist(playlistEntity: PlaylistEntity)
}
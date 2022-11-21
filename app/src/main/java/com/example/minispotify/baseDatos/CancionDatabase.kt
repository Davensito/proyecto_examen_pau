package com.example.minispotify.baseDatos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minispotify.dao.ArtistaDao
import com.example.minispotify.dao.CancionDao
import com.example.minispotify.dao.PlaylistDao
import com.example.minispotify.dao.UsuarioDao
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.PlaylistEntity
import com.example.minispotify.objetos.UsuarioEntity

@Database(entities = arrayOf(CancionEntity::class, UsuarioEntity::class, ArtistaEntity::class, PlaylistEntity::class), version = 1)
abstract class CancionDatabase : RoomDatabase(){
    abstract fun cancionDao(): CancionDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun artistaDao(): ArtistaDao
    abstract fun playlistDao(): PlaylistDao
}
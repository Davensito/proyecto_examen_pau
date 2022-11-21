package com.example.minispotify.dao

import androidx.room.*
import com.example.minispotify.objetos.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    fun getAllUsuarios(): MutableList<UsuarioEntity>
    @Query("SELECT * FROM Usuario WHERE username = :username and password = :password")
    fun getUsuario(username: String, password: String): UsuarioEntity
    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)
    @Update
    fun updateUsuario(usuarioEntity: UsuarioEntity)
    @Delete
    fun deleteUsuario(usuarioEntity: UsuarioEntity)

}
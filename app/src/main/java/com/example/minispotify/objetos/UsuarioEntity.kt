package com.example.minispotify.objetos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
                    var id: Long = 0,
                    var username: String,
                    var password: String,
                    var email: String="") {

}
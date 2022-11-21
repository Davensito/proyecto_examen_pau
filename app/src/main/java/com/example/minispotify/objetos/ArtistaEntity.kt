package com.example.minispotify.objetos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Artista")
class ArtistaEntity(
    @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var nombre: String,
        var imagen: String) {

}
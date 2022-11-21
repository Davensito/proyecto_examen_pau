package com.example.minispotify.objetos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Playlist")
class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var titulo: String,
    var imagen: String = "") {
}
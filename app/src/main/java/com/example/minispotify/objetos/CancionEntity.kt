package com.example.minispotify.objetos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.bottomnavigation.BottomNavigationView

@Entity(tableName = "Cancion")
class CancionEntity(
    @PrimaryKey(autoGenerate = true)
                    var id: Long = 0,
                    var titulo: String,
                    var autor: String,
                    var imagen: String = "",
                    var musica: String,
                    var isFavorite: Boolean){
}
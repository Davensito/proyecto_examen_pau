package com.example.minispotify.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity

@Dao
interface ArtistaDao {
    @Query("SELECT * FROM Artista")
    fun getAllArtistas(): MutableList<ArtistaEntity>
    @Query("SELECT * FROM Artista WHERE nombre = :nombre")
    fun getArtista(nombre: String): ArtistaEntity
    @Query("INSERT INTO Artista(nombre,imagen)" +
            "VALUES" +
            "('Lady Gaga','https://phantom-telva.unidadeditorial.es/daa17a84f92d8db3347d41c811067c90/crop/0x63/1497x962/resize/1280/f/webp/assets/multimedia/imagenes/2020/03/16/15843755691245.jpg')," +
            "('Twenty One Pilots','https://indiehoy.com/wp-content/uploads/2018/07/Twenty-One-Pilots.jpg')," +
            "('Post Malone','https://conciertos.club/doc/a/2020/a_postmalone.jpg')," +
            "('Imagine Dragons','https://media.resources.festicket.com/www/artists/ImagineDragons_New2.jpg');")
    fun artistasPre()
    @Query("SELECT * FROM Cancion WHERE autor = :nombre")
    fun getArtistaSongs(nombre: String): MutableList<CancionEntity>
    @Insert
    fun addArtista(artistaEntity: ArtistaEntity)
    @Update
    fun updateArtista(artistaEntity: ArtistaEntity)
    @Delete
    fun deleteArtista(artistaEntity: ArtistaEntity)
}
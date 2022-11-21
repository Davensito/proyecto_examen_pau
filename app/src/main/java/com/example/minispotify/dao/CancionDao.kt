package com.example.minispotify.dao

import androidx.room.*
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.UsuarioEntity

@Dao
interface CancionDao {
    @Query("SELECT * FROM Cancion")
    fun getAllCanciones(): MutableList<CancionEntity>
    @Query("INSERT INTO Cancion(titulo,autor,imagen,musica,isFavorite)" +
            "VALUES" +
            "('Bones','Imagine Dragons','https://www.lahiguera.net/musicalia/artistas/imagine_dragons/disco/12045/tema/27145/imagine_dragons_bones-portada.jpg','bones','true')," +
            "('Radioactive','Imagine Dragons','https://i.pinimg.com/originals/6c/94/7f/6c947f2469bc978f4283d06cacb9c30e.png','radioactive','false')," +
            "('Believer','Imagine Dragons','https://i1.sndcdn.com/artworks-s3zOCWcV8XQVtQcv-0emq8A-t500x500.jpg','believer','false')," +
            "('Enemy','Imagine Dragons','https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0cd265130636097.61842df318d10.jpg','enemy','true')," +
            "('Natural','Imagine Dragons','https://www.lahiguera.net/musicalia/artistas/imagine_dragons/disco/9450/tema/18956/imagine_dragons_natural-portada.jpg','natural','false')," +
            "('Thunder','Imagine Dragons','https://www.lahiguera.net/musicalia/artistas/imagine_dragons/disco/8369/tema/15725/imagine_dragons_thunder-portada.jpg','thunder','false')," +
            "('Judas','Lady Gaga','https://static.wikia.nocookie.net/gaga/images/7/7f/Judas1.png/revision/latest?cb=20130523003247&path-prefix=es','judas','true')," +
            "('Poker Face','Lady Gaga','https://lastfm.freetls.fastly.net/i/u/770x0/bd4ebf4ae95e4e3da772c5de1d37599c.jpg','poker_face','false')," +
            "('Paparazzi','Lady Gaga','https://lastfm.freetls.fastly.net/i/u/770x0/9c5f91e91d264509bcbcaacb1dcf58e3.jpg','paparazzi','false')," +
            "('Bad Romance','Lady Gaga','https://2.bp.blogspot.com/-BModyGrj2Do/Th7VBT3XwXI/AAAAAAAAJ4w/Oflokxxtu-8/s1600/BadRomance.JPG','bad_romance','false')," +
            "('Clhorine','Twenty One Pilots','https://i.pinimg.com/originals/3e/20/71/3e2071632ec8c3ac87ec2b2f7c9434b6.jpg','clhorine','true')," +
            "('Stressed Out','Twenty One Pilots','https://lastfm.freetls.fastly.net/i/u/770x0/b48ec7d507a34806e12608560d4fd2a1.jpg','stressed_out','false')," +
            "('Car Radio','Twenty One Pilots','https://lastfm.freetls.fastly.net/i/u/ar0/021705b66e1f97b966402d153af1fc15','car_radio','true')," +
            "('Stay','Post Malone','https://i1.sndcdn.com/artworks-000348676827-068azf-t500x500.jpg','stay','true')," +
            "('Nico And The Niners', 'Twenty One Pilots','https://i.scdn.co/image/ab67616d0000b2737541fcd3b75203f1cef7ff55','nico_and_the_niners', 'false')," +
            "('Better Now','Post Malone','https://m.media-amazon.com/images/M/MV5BZGE2NjRiNGMtNGEzYi00MDk1LThmNmYtZDU0MGMxMjQyNzYxXkEyXkFqcGdeQXVyNDg4MjkzNDk@._V1_.jpg','better_now','false')," +
            "('Applause','Lady Gaga','https://i1.sndcdn.com/artworks-000056472685-fcjb62-t500x500.jpg','applause','false');")
    //faltan canciones de stressed out, car radio, stay y better now
    fun cancionesPre()
    @Query("SELECT * FROM Cancion WHERE isFavorite = 'true'")
    fun getCancionesFav(): MutableList<CancionEntity>
    @Query("SELECT * FROM Cancion WHERE autor = :nombre")
    fun getCancionesArtista(nombre: String): MutableList<CancionEntity>
    @Insert
    fun addCancion(cancionEntity: CancionEntity)
    @Update
    fun updateCancion(cancionEntity: CancionEntity)
    @Delete
    fun deleteCancion(cancionEntity: CancionEntity)

}
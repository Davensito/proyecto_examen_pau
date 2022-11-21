package com.example.minispotify.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.OnClickListener
import com.example.minispotify.R
import com.example.minispotify.adapter.ArtistaAdapter
import com.example.minispotify.adapter.CancionAdapter
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityMainBinding
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.PlaylistEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cancionAdapter: CancionAdapter
    private lateinit var artistaAdapter: ArtistaAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var gridLayoutManagerCanciones: RecyclerView.LayoutManager
    private lateinit var gridLayoutManagerArtistas: RecyclerView.LayoutManager

    private var audioCancion : String? = null

    //MUSICA
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Thread {
            CancionApplication.database.cancionDao().cancionesPre()
            CancionApplication.database.artistaDao().artistasPre()
            CancionApplication.database.playlistDao().playlistPre()
        }.start()
        Thread.sleep(200)*/

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.background = null

        cancionAdapter = CancionAdapter(mutableListOf(), this)
        artistaAdapter = ArtistaAdapter(mutableListOf(), this)
        gridLayoutManagerCanciones = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        gridLayoutManagerArtistas = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        getCanciones()
        getArtistas()

        binding.recyclerViewCanciones.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManagerCanciones
            adapter = cancionAdapter
        }

        binding.recyclerViewArtistas.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManagerArtistas
            adapter = artistaAdapter
        }

        binding.bottomNavigation.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.playlist -> {
                    startActivity(Intent(this, PlaylistActivity::class.java))
                    true
                }
                R.id.perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                R.id.favSongs -> {
                    startActivity(Intent(this, FavSongsActivity::class.java))
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }
        }
    }

    private fun getCancionesFav(): MutableList<ArtistaEntity> {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()

        when ((1..3).random()) {
            1 -> {
                Glide.with(this)
                    .load("https://i.blogs.es/a2de56/spotify/450_1000.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(binding.imgAnuncio)
            }
            2 -> {
                Glide.with(this)
                    .load("https://topicflower.com/blog/wp-content/uploads/2021/12/Publicidad-en-spotify-blog.png")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(binding.imgAnuncio)

            }
            else -> {
                Glide.with(this)
                    .load("https://aplicacionesandroid.es/img/Spotify-Premium-Gratis-APK.webp")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(binding.imgAnuncio)
            }
        }

        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()

    }

    override fun onStart() {
        super.onStart()

        if (mediaPlayer != null) {
            position = mediaPlayer!!.currentPosition
        }
        //mediaPlayer?.seekTo(position)
        //mediaPlayer?.start()
    }

    /*override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()
        if (mediaPlayer != null) {
            position = mediaPlayer!!.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }*/

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun getCanciones() {
        doAsync {
            val cancionEntity = CancionApplication.database.cancionDao().getAllCanciones()
            uiThread {
                cancionAdapter.setCanciones(cancionEntity)
            }
        }
    }

    private fun getArtistas(){
        doAsync {
            val artistaEntity = CancionApplication.database.artistaDao().getAllArtistas()
            uiThread {
                artistaAdapter.setArtistas(artistaEntity)
            }
        }
    }

    override fun onClick(cancionEntity: CancionEntity) {
        val resId = this.resources.getIdentifier(cancionEntity.musica,"raw",this.packageName)
        audioCancion = cancionEntity.musica

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        mediaPlayer = MediaPlayer.create(this, resId)
        mediaPlayer?.start()
    }

    override fun onClick(artistaEntity: ArtistaEntity) {
        val intent = Intent(this, ArtistaActivity::class.java).putExtra("imagen",artistaEntity.imagen).putExtra("nombre",artistaEntity.nombre)
        startActivity(intent)
    }

    override fun onClick(playlistEntity: PlaylistEntity) {
        TODO("Not yet implemented")
    }

    override fun onPauseCancion(cancionEntity: CancionEntity) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDeletePlaylist(playlistEntity: PlaylistEntity) {
        TODO("Not yet implemented")
    }

    override fun onEditPlaylist(playlistEntity: PlaylistEntity) {
        TODO("Not yet implemented")
    }

}
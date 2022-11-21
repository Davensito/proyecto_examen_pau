package com.example.minispotify.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.OnClickListener
import com.example.minispotify.R
import com.example.minispotify.adapter.CancionArtistaAdapter
import com.example.minispotify.adapter.FavSongsAdapter
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityArtistaBinding
import com.example.minispotify.databinding.ActivityMainBinding
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.PlaylistEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ArtistaActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityArtistaBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var cancionArtistaAdapter: CancionArtistaAdapter
    private lateinit var gridLayoutManagerCancionesArtista: RecyclerView.LayoutManager

    //MUSICA
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.background = null

        cancionArtistaAdapter = CancionArtistaAdapter(mutableListOf(),this)
        gridLayoutManagerCancionesArtista = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        getCancionesArtista()

        binding.recyclerViewCancionesArtista.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManagerCancionesArtista
            adapter = cancionArtistaAdapter
        }

        val artistaImagen = intent.extras?.get("imagen")
        Glide.with(this)
            .load(artistaImagen)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.imgArtista)

        binding.bottomNavigation.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.playlist -> {
                    startActivity(Intent(this, PlaylistActivity::class.java))
                    true
                }
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
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

    private fun getCancionesArtista() {
        val artistaNombre = intent.extras?.get("nombre").toString()
        doAsync {
            val cancionEntity = CancionApplication.database.cancionDao().getCancionesArtista(artistaNombre)
            uiThread {
               cancionArtistaAdapter.setCanciones(cancionEntity)
            }
        }

    }

    override fun onClick(cancionEntity: CancionEntity) {
        val resId = this.resources.getIdentifier(cancionEntity.musica,"raw",this.packageName)
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        mediaPlayer = MediaPlayer.create(this, resId)
        mediaPlayer?.start()
    }

    override fun onClick(artistaEntity: ArtistaEntity) {
        TODO("Not yet implemented")
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
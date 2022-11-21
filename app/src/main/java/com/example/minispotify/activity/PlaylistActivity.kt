package com.example.minispotify.activity

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.OnClickListener
import com.example.minispotify.R
import com.example.minispotify.adapter.ArtistaAdapter
import com.example.minispotify.adapter.CancionAdapter
import com.example.minispotify.adapter.PlaylistAdapter
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityMainBinding
import com.example.minispotify.databinding.ActivityPlaylistBinding
import com.example.minispotify.objetos.ArtistaEntity
import com.example.minispotify.objetos.CancionEntity
import com.example.minispotify.objetos.PlaylistEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random

class PlaylistActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityPlaylistBinding
    private lateinit var playlistAdapter: PlaylistAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var gridLayoutManagerPlaylist: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.background = null

        playlistAdapter = PlaylistAdapter(mutableListOf(), this)
        gridLayoutManagerPlaylist = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        getPlaylists()

        binding.recyclerViewPlaylists.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManagerPlaylist
            adapter = playlistAdapter
        }

        binding.bottomNavigation.setOnItemSelectedListener { item->
            when (item.itemId) {
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

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, NewPlaylistActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPlaylists(){
        doAsync {
            val playlistEntity = CancionApplication.database.playlistDao().getAllPlaylists()
            uiThread {
                playlistAdapter.setPlaylists(playlistEntity)
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

        playlistAdapter.update()

    }


    override fun onClick(cancionEntity: CancionEntity) {
        TODO("Not yet implemented")
    }

    override fun onClick(artistaEntity: ArtistaEntity) {
        TODO("Not yet implemented")
    }

    override fun onClick(playlistEntity: PlaylistEntity) {
        TODO("Not yet implemented")
    }

    override fun onPauseCancion(cancionEntity: CancionEntity) {
        TODO("Not yet implemented")
    }

    override fun onDeletePlaylist(playlistEntity: PlaylistEntity) {
        doAsync {
            CancionApplication.database.playlistDao().deletePlaylist(playlistEntity)
            uiThread {
                playlistAdapter.delete(playlistEntity)
            }
        }
    }

    override fun onEditPlaylist(playlistEntity: PlaylistEntity) {
        val intent = Intent(this, EditplaylistActivity::class.java).
        putExtra("titulo",playlistEntity.titulo).
        putExtra("imagen",playlistEntity.imagen)
        startActivity(intent)
        playlistAdapter.updatePlaylist(playlistEntity)
    }
}
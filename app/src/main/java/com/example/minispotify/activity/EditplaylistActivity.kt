package com.example.minispotify.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minispotify.adapter.PlaylistAdapter
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityEditplaylistBinding
import com.example.minispotify.databinding.ActivityNewPlaylistBinding
import com.example.minispotify.objetos.PlaylistEntity
import com.google.android.material.snackbar.Snackbar

class EditplaylistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditplaylistBinding
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditplaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEditarPlaylist.setOnClickListener {
            var titulo = binding.etTitulo.text.toString()
            var imagen = binding.etImagen.text.toString()

            val playlistEntity = PlaylistEntity(titulo = titulo, imagen = imagen)

            if (!titulo.isEmpty()) {
                //GUARDAR EN LA BASE DE DATOS
                Thread {
                    CancionApplication.database.playlistDao().updatePlaylist(playlistEntity)
                }.start()
                val intent = Intent(this, PlaylistActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Debes ponerle titulo a la playlist", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
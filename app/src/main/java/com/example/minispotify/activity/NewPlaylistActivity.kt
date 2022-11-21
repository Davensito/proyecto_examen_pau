package com.example.minispotify.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityNewPlaylistBinding
import com.example.minispotify.objetos.PlaylistEntity
import com.google.android.material.snackbar.Snackbar

class NewPlaylistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearPlaylist.setOnClickListener {
            var titulo = binding.etTitulo.text.toString()
            var imagen = binding.etImagen.text.toString()

            val playlistEntity = PlaylistEntity(
                titulo = binding.etTitulo.text.toString().trim(),
                imagen = binding.etImagen.text.toString().trim(),
            )

            if (!titulo.isEmpty()) {
                //GUARDAR EN LA BASE DE DATOS
                Thread {
                    CancionApplication.database.playlistDao().addPlaylist(playlistEntity)
                }.start()
                val intent = Intent(this, PlaylistActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(it, "Debes ponerle titulo a la playlist", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
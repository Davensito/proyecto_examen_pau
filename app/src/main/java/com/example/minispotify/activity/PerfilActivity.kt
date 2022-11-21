package com.example.minispotify.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.R
import com.example.minispotify.databinding.ActivityPerfilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.inputmethod.InputMethodManager

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.background = null

        Glide.with(this)
            .load("https://www.softzone.es/app/uploads/2018/04/guest.png")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.imgPerfil)

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
                R.id.favSongs -> {
                    startActivity(Intent(this, FavSongsActivity::class.java))
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }
        }

        binding.btnDarkMode.setOnClickListener {
            ponerModoOscuro(it)
        }

        binding.btnLigthMode.setOnClickListener {
            ponerModoClaro(it)
        }
    }

    private fun ponerModoClaro(view: View?) {
        binding.apply {
            btnDarkMode.visibility = View.VISIBLE
            btnLigthMode.visibility = View.GONE
        }
    }

    private fun ponerModoOscuro(view: View?) {
        binding.apply {
            btnDarkMode.visibility = View.GONE
            btnLigthMode.visibility = View.VISIBLE
        }
    }
}
package com.example.minispotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.minispotify.R
import com.example.minispotify.baseDatos.CancionApplication
import com.example.minispotify.databinding.ActivityLoginBinding
import com.example.minispotify.objetos.UsuarioEntity
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val userDefect = preferences.getString("usuario", "")

        if(userDefect!!.isNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        Glide.with(this)
            .load("https://storage.googleapis.com/pr-newsroom-wp/1/2018/11/Spotify_Logo_CMYK_Green.png")
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.imgPortada)


        binding.btnLogin.setOnClickListener {
            var usuario = binding.etUser.text.toString()
            var password = binding.etPass.text.toString()
            var isUserInBd = false
            if(!usuario.isEmpty() && !password.isEmpty()){
                doAsync {
                    val usuarioBd = CancionApplication.database.usuarioDao().getUsuario(usuario, password)
                    if (usuarioBd.username.isNotEmpty()){
                        isUserInBd = true
                        if (binding.cbRecordar.isChecked){
                            preferences.edit().putString("usuario",usuario).commit()
                            preferences.edit().putString("password",password).commit()
                        }else{
                            preferences.edit().clear().commit()
                        }
                    }
                }

                Thread.sleep(70)
                if(isUserInBd){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Snackbar.make(binding.root,"Usuario no registrado", Snackbar.LENGTH_LONG).show()
                }

            }else{
                Snackbar.make(it,"Debes rellenar todos los campos", Snackbar.LENGTH_LONG).show()
            }
        }

        binding.btnRegis.setOnClickListener {
            var usuarioEt = binding.etUser.text.toString()
            var passwordEt = binding.etPass.text.toString()

            val usuario = UsuarioEntity(username = usuarioEt, password = passwordEt)

            if(!usuarioEt.isEmpty() && !passwordEt.isEmpty()){
                //GUARDAR EN LA BASE DE DATOS
                doAsync{
                    CancionApplication.database.usuarioDao().addUsuario(usuario)
                    uiThread {
                        Snackbar.make(binding.root,"Usuario registrado correctamente", Snackbar.LENGTH_LONG).show()
                    }
                }

            }else{
                Snackbar.make(it,"Debes rellenar todos los campos", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
package com.example.minispotify.baseDatos

import android.app.Application
import androidx.room.Room

class CancionApplication : Application() {
    companion object {
        lateinit var database: CancionDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            CancionDatabase::class.java,
            "CancionDatabase")
            .build()
    }
}
package com.example.shaadiapp.view.activities

import android.app.Application
import org.koin.android.java.KoinAndroidApplication.create
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val koin = create(this)
        startKoin(GlobalContext(), koin)
    }
}
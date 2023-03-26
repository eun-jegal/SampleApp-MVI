package com.example.sampleapp_mvi

import android.app.Application
import com.example.sampleapp_mvi.di.AppComponent
import com.example.sampleapp_mvi.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
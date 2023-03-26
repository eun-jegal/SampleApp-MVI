package com.example.sampleapp_mvi.data.model

sealed class MainIntent {
    object FetchUser: MainIntent()
}
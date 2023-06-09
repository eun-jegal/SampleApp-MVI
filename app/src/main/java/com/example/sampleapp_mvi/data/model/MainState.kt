package com.example.sampleapp_mvi.data.model

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Users(val user: List<UserItem>): MainState()
    data class Error(val error: String?): MainState()
}

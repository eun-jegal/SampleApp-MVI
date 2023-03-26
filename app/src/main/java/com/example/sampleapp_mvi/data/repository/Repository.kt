package com.example.sampleapp_mvi.data.repository

import com.example.sampleapp_mvi.data.model.UserItem

interface Repository {
    suspend fun getUsers(): List<UserItem>
}
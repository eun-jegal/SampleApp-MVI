package com.example.sampleapp_mvi.data.repository

import com.example.sampleapp_mvi.data.model.Users

interface Repository {
    suspend fun getUsers(): Users
}
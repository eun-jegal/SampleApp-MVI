package com.example.sampleapp_mvi.data.repository

import com.example.sampleapp_mvi.data.api.APIService
import com.example.sampleapp_mvi.data.model.Users

class RepositoryImpl(
    private val apiService: APIService
): Repository {
    override suspend fun getUsers(): Users {
        return apiService.getUsers()
    }
}
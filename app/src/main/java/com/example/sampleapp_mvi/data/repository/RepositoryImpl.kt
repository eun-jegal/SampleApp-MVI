package com.example.sampleapp_mvi.data.repository

import com.example.sampleapp_mvi.data.model.UserItem
import com.example.sampleapp_mvi.data.api.APIService

class RepositoryImpl(
    private val apiService: APIService
): Repository {
    override suspend fun getUsers(): List<UserItem> {
        return apiService.getUsers()
    }
}
package com.example.sampleapp_mvi.data.api

import com.example.sampleapp_mvi.data.model.Users
import retrofit2.http.GET

interface APIService {
    @GET("users")
    suspend fun getUsers(): Users
}
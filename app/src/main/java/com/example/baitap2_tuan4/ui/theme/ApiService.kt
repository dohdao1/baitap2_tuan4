package com.example.baitap2_tuan4.ui.theme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("tasks")
    suspend fun getTask(): List<Task>

    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): Task

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}
object RetrofitInstance{
    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl("https://amock.io/api/researchUTH/").addConverterFactory(
            GsonConverterFactory.create()).build().create(ApiService::class.java)
    }
}
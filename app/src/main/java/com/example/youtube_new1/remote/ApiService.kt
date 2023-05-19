package com.example.youtube_new1.remote

import com.example.youtube_new1.remote.model.PlayLists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlayLists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String
    ): Call<PlayLists>

}
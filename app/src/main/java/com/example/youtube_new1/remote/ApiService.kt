package com.example.youtube_new1.remote

import com.example.youtube_new1.remote.model.PlayLists
import com.example.youtube_new1.remote.model.PlaylistsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlayLists(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String
    ): Response<PlayLists>

    @GET("playlistItems")
    suspend fun playlistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("maxResults") maxResults : Int
    ): Response<PlaylistsItem>

}
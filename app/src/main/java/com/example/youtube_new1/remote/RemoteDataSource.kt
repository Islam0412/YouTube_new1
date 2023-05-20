package com.example.youtube_new1.remote

import com.example.youtube_new1.BuildConfig
import com.example.youtube_new1.core.netvork.BaseDataSource
import com.example.youtube_new1.core.netvork.RetrofitClient
import com.example.youtube_new1.core.netvork.result.Resource
import com.example.youtube_new1.remote.model.PlayLists
import com.example.youtube_new1.remote.model.PlaylistsItem
import com.example.youtube_new1.utils.Const

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService = RetrofitClient.create()

    suspend fun getPlayLists(): Resource<PlayLists> {
        return getResult {
            apiService.getPlayLists(
                BuildConfig.API_KEY,
                Const.part,
                Const.channelId
            )
        }
    }

    suspend fun getDetail(playlistId: String, itemCount: Int):Resource<PlaylistsItem> {
        return getResult {
            apiService.playlistItems(
                BuildConfig.API_KEY,
                Const.part,
                playlistId,
                itemCount
            )
        }
    }
}
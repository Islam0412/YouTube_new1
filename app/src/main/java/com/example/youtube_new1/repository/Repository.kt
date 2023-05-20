package com.example.youtube_new1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube_new1.core.netvork.result.Resource
import com.example.youtube_new1.remote.RemoteDataSource
import com.example.youtube_new1.remote.model.PlayLists
import com.example.youtube_new1.remote.model.PlaylistsItem
import kotlinx.coroutines.Dispatchers


class Repository {

    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlayLists(): LiveData<Resource<PlayLists>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlayLists()
            emit(response)
        }
    }

    fun getDetail(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistsItem>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getDetail(playlistId, itemCount)
            emit(response)
        }
    }

}
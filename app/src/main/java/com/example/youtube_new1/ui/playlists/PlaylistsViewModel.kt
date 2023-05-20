package com.example.youtube_new1.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_new1.App
import com.example.youtube_new1.BuildConfig
import com.example.youtube_new1.core.ui.BaseViewModel
import com.example.youtube_new1.remote.ApiService
import com.example.youtube_new1.core.netvork.RetrofitClient
import com.example.youtube_new1.core.netvork.result.Resource
import com.example.youtube_new1.remote.model.PlayLists
import retrofit2.Call
import retrofit2.Response

class PlaylistsViewModel: BaseViewModel() {

    fun playlists(): LiveData<Resource<PlayLists>>{
        return App.repository.getPlayLists()
    }
}
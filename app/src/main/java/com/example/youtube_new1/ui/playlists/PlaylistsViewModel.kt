package com.example.youtube_new1.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_new1.BuildConfig
import com.example.youtube_new1.core.ui.BaseViewModel
import com.example.youtube_new1.remote.ApiService
import com.example.youtube_new1.core.netvork.RetrofitClient
import com.example.youtube_new1.remote.model.PlayLists
import retrofit2.Call
import retrofit2.Response

class PlaylistsViewModel: BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<PlayLists>{
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<PlayLists> {
        val data = MutableLiveData<PlayLists>()
        apiService.getPlayLists(BuildConfig.API_KEY,"contentDetails","UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object: retrofit2.Callback<PlayLists>{
                override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                    print(t.stackTrace)
                    //404 - not found - страница не найдина , 401 - token invalid - токен истек, 403 - access denied - нет доступа
                }
            })
        return data
    }
}
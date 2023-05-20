package com.example.youtube_new1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_new1.BuildConfig
import com.example.youtube_new1.core.netvork.RetrofitClient
import com.example.youtube_new1.core.netvork.result.Resource
import com.example.youtube_new1.remote.ApiService
import com.example.youtube_new1.remote.model.PlayLists
import retrofit2.Call
import retrofit2.Response


class Repository {
    private val apiService: ApiService = RetrofitClient.create()

    fun getPlayLists(): LiveData<Resource<PlayLists>> {
        val data = MutableLiveData<Resource<PlayLists>>()
        data.value = Resource.loading()

        apiService.getPlayLists(
            BuildConfig.API_KEY,
            CONTENT_DETAILS_SNIPPET,
            CHANELL_ID
        ).enqueue(object : retrofit2.Callback<PlayLists> {
            override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                print(t.stackTrace)
                data.value = t.message?.let { Resource.error(it, null, 400) }

                //404 - not found, 401 - token invalid, 403 - access denied
            }
        })
        return data
    }

    companion object {
        const val CONTENT_DETAILS_SNIPPET = "contentDetails,snippet"
        const val CHANELL_ID = "UCWOA1ZGywLbqmigxE4Qlvuw"
    }
}
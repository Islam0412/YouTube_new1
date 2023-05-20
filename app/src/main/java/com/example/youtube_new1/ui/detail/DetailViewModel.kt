package com.example.youtube_new1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_new1.App
import com.example.youtube_new1.core.netvork.result.Resource
import com.example.youtube_new1.core.ui.BaseViewModel
import com.example.youtube_new1.remote.model.Item
import com.example.youtube_new1.remote.model.PlaylistsItem

class DetailViewModel: BaseViewModel() {

    private val mutableDetailId: MutableLiveData<List<String>> = MutableLiveData()
    val liveDetailId: LiveData<List<String>> = mutableDetailId

    fun getDetail(playListId: String, itemCount: Int): LiveData<Resource<PlaylistsItem>>{
        return App.repository.getDetail(playListId, itemCount)
    }

    fun getDetailId(data: List<Item>){
        val result = arrayListOf<String>()
        for (i in data) {
            result.add(i.contentDetails.videoId)
        }
        mutableDetailId.value = result
    }

}
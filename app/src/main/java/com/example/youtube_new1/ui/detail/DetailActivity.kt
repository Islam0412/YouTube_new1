package com.example.youtube_new1.ui.detail

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_new1.core.ext.ConnectionLiveData
import com.example.youtube_new1.core.netvork.result.Status
import com.example.youtube_new1.core.ui.BaseActivity
import com.example.youtube_new1.databinding.ActivityDetailBinding
import com.example.youtube_new1.remote.model.Item
import com.example.youtube_new1.remote.model.PlaylistInfo
import com.example.youtube_new1.ui.detail.adapter.DetailAdapter
import com.example.youtube_new1.ui.playlists.PlaylistsActivity

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private val adapter by lazy { DetailAdapter() }

    private val playlistInfo by lazy { intent.getSerializableExtra(DETAIL_KEY) as PlaylistInfo }
    private var playlistItemData = listOf<Item>()
    private var videosId = arrayListOf<String>()
    override lateinit var viewModel: DetailViewModel

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        getDetail()
    }

    private fun getDetail() {
        viewModel.getDetail(playlistInfo.id, playlistInfo.itemCount).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    playlistItemData = it.data!!.items
                    getDetailId()
                    adapter.addData(playlistItemData)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> Log.e("ololo", "LOADING: ")
            }
        }
    }

    private fun getDetailId() {
        viewModel.getDetailId(playlistItemData)
        viewModel.liveDetailId.observe(this) {
            videosId.addAll(it)
        }
    }

    override fun initListener() {
        super.initListener()
        val intentBack = Intent(this@DetailActivity, PlaylistsActivity::class.java)
        binding.backTv.setOnClickListener { finish() }
    }

    companion object {
        const val DETAIL_KEY = "detail_key"
    }
}
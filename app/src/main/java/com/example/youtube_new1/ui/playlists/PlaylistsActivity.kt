package com.example.youtube_new1.ui.playlists

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_new1.core.ext.ConnectionLiveData
import com.example.youtube_new1.core.ui.BaseActivity
import com.example.youtube_new1.databinding.ActivityPlaylistsBinding
import com.example.youtube_new1.remote.model.Item
import com.example.youtube_new1.remote.model.PlayLists
import com.example.youtube_new1.remote.model.PlaylistInfo
import com.example.youtube_new1.ui.detail.DetailActivity
import com.example.youtube_new1.ui.playlists.adapter.PlaylistsAdapter

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun initView() {
        super.initViewModel()
        adapter = PlaylistsAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this){
            if (it){
                binding.internetConnection.visibility = View.VISIBLE
                binding.noInternetConnection.visibility = View.GONE
            }else {
                binding.internetConnection.visibility = View.GONE
                binding.noInternetConnection.visibility = View.VISIBLE
                initViewModel()
            }

        }
    }
    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter
            viewModel.playlists().observe(this) {
                it.data?.let { it1 -> adapter.addList(it1.items) }
            }
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(
            DetailActivity.DETAIL_KEY, PlaylistInfo(
                item.id,
                item.snippet.title,
                item.snippet.description,
                item.contentDetails.itemCount
            )
        )
    }

    companion object {
        const val ID = "ID"
    }

}
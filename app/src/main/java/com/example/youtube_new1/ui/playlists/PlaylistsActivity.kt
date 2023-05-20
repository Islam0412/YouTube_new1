package com.example.youtube_new1.ui.playlists

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_new1.core.ext.ConnectionLiveData
import com.example.youtube_new1.core.ui.BaseActivity
import com.example.youtube_new1.databinding.ActivityPlaylistsBinding
import com.example.youtube_new1.remote.model.PlayLists
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
            adapter.addList(it.items!! as List<PlayLists.Item>)
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(item: PlayLists.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        startActivity(intent)
    }

    companion object {
        const val ID = "ID"
    }

}
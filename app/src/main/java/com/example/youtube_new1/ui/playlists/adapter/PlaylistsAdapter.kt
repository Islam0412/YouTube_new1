package com.example.youtube_new1.ui.playlists.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube_new1.databinding.ItemPlaylistBinding
import com.example.youtube_new1.remote.model.Item
import com.example.youtube_new1.remote.model.PlayLists

class PlaylistsAdapter(private val onClick: (item: Item) -> Unit) :
    RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    private var list = ArrayList<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Item>) {
        this.list = list as ArrayList<Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Item?) {
                with(binding) {
                    binding.ivPlaylist.load(item?.snippet?.thumbnails?.default?.url)
                    binding.tvPlaylistName.text = item?.snippet!!.title
                    binding.tvNumberOfVideos.text = "${item.contentDetails?.itemCount} video"

                    cvPlaylist.setOnClickListener {
                        onClick.invoke(item)
                    }
            }
        }
    }
}
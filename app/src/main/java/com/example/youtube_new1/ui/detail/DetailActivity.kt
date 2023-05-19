package com.example.youtube_new1.ui.detail

import androidx.lifecycle.ViewModelProvider
import com.example.youtube_new1.core.ui.BaseActivity
import com.example.youtube_new1.databinding.ActivityDetailBinding

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initViewModel()
    }


}
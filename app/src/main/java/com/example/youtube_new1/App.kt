package com.example.youtube_new1

import android.app.Application
import com.example.youtube_new1.repository.Repository

class App : Application() {

    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }

}
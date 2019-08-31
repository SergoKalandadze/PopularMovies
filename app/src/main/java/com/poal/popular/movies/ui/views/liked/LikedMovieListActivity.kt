package com.poal.popular.movies.ui.views.liked

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.poal.popular.movies.R

class LikedMovieListActivity : AppCompatActivity() {

    private val fragmentList:  LikedMovieListFragment = LikedMovieListFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentList)
                .commitNow()
        }
    }

    companion object {

        fun start(from: Context) {
            val intent = Intent(from, LikedMovieListActivity::class.java)
            from.startActivity(intent)
        }
    }


}


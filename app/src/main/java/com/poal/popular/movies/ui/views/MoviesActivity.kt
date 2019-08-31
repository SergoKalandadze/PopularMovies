package com.poal.popular.movies.ui.views

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.poal.popular.movies.ui.views.MovieDetailFragment.OnFragmentInteractionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.poal.popular.movies.R
import com.poal.popular.movies.ui.views.liked.LikedMovieListActivity
import com.poal.popular.movies.ui.views.liked.OnShowLiked


class MoviesActivity : AppCompatActivity(),
    OnFragmentInteractionListener, OnMovieSelected, OnShowLiked {

    private val fragmentList:  MovieListFragment = MovieListFragment.newInstance()
    private lateinit var fragmentDetails: MovieDetailFragment
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentList)
                    .commitNow()
        }
    }

    override fun onShowLikedClick() {

        LikedMovieListActivity.start(this)
    }

    override fun onMovieSelected(movieId: Int) {

        fragmentDetails = MovieDetailFragment.newInstance(movieId)

        transaction = supportFragmentManager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setCustomAnimations(R.animator.slide_in_down, R.animator.fade_out);

        transaction.replace(R.id.container, fragmentDetails);
        transaction.addToBackStack(fragmentDetails::class.simpleName)

        transaction.commit();

    }

    override fun onFragmentInteraction(uri: Uri) {

    }


}

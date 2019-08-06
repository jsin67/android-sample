package com.costa.jaggratsample.view.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.costa.jaggratsample.R
import com.costa.jaggratsample.models.Venue
import com.costa.jaggratsample.presenter.SearchPopularPlacePresenter
import com.costa.jaggratsample.verifyAvailableNetwork
import com.costa.jaggratsample.view.PlaceAdapter
import com.costa.jaggratsample.view.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class SearchPopularPlaceActivity : BaseActivity<SearchPopularPlacePresenter>(), SearchView {
    @Inject
    lateinit var searchPopularPlacePresenter: SearchPopularPlacePresenter;
    private lateinit var progressBar: ProgressBar
    private val adapter = PlaceAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        place_list.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(it.context, RecyclerView.VERTICAL))
        }
        progressBar = this.loading
        btn_find.setOnClickListener {
            if(verifyAvailableNetwork(this)){
                searchPopularPlacePresenter.getPopularPlaceDetails(getString(R.string.client_id),
                    getString(R.string.client_secret), et_place.text.toString())
            } else {
                searchPopularPlacePresenter.noInternetConnection()
            }
        }
    }

    override fun showPlaces(venues: List<Venue>) {
        adapter.setVenueData(venues)
    }

    override fun instantiatePresenter(): SearchPopularPlacePresenter {
        searchPopularPlacePresenter  = SearchPopularPlacePresenter(this);
        return searchPopularPlacePresenter
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
    }

}

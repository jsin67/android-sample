package com.costa.jaggratsample.presenter

import com.costa.jaggratsample.ERROR_MESSAGE
import com.costa.jaggratsample.INTERNET_MESSAGE
import com.costa.jaggratsample.models.ResponseJSON
import com.costa.jaggratsample.models.Venue
import com.costa.jaggratsample.repository.PopularPlaceRepository
import com.costa.jaggratsample.view.BaseView
import com.costa.jaggratsample.view.SearchView
import javax.inject.Inject

class SearchPopularPlacePresenter constructor(view: SearchView): BasePresenter<BaseView>(view), PopularPlaceRepository.RepositoryCallBack {

    private val searchView = view;

    @Inject
    lateinit var repository: PopularPlaceRepository;


    /**
     * Calls repository to get the information about the place.
     * @param clientId: Client id for API
     * @param clientSecret: Client Secret for API.
     * @param placeName: Name of the place.
     */
    fun getPopularPlaceDetails(clientId: String, clientSecret: String, placeName: String) {
        searchView.showLoading()
        searchView.showPlaces(emptyList())
        if(validateParams(clientId, clientSecret, placeName)) {
            repository.getNearByPlaces(clientId, clientSecret, placeName, this)
        } else {
            searchView.stopLoading()
            searchView.showError("Please put the more accurate information !!")
        }
    }

    fun noInternetConnection() {
        searchView.showError(INTERNET_MESSAGE)
    }
    /**
     * Calls repository to get the information about the place.
     * @param clientId: Client id for API
     * @param clientSecret: Client Secret for API.
     * @param placeName: Name of the place.
     *
     * @return : true if all the params are not blank
     */
    private fun validateParams(clientId: String, clientSecret: String, placeName: String): Boolean {
        return clientId.isNotBlank() && clientSecret.isNotBlank() && placeName.isNotBlank()
    }

    /**
     * Callback when API call is completed.
     * @param json: Response of API
     * @param error: Throwable if there is any error
     */
    override fun onResponse(json: ResponseJSON?, error: Throwable?) {
        searchView.stopLoading()
        if(json?.response != null && !json?.response?.groups.isEmpty()){
            searchView.showPlaces(prepareUIModel(json))
        } else {
            searchView.showError(ERROR_MESSAGE)
        }

    }

    /**
     * Creates model for View.
     * @param json API response
     *
     * @return list of all the venue details
     */
    private fun prepareUIModel(json: ResponseJSON?): List<Venue>{
        val venue = arrayListOf<Venue>()
        json?.response?.groups?.forEach { it.items.forEach{ venue.add(it.venue)} }
        return venue
    }
}
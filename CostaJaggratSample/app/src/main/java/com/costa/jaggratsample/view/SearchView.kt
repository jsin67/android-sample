package com.costa.jaggratsample.view

import com.costa.jaggratsample.models.Venue

/**
 * Represents view for Search and display for Venue.
 */
interface SearchView :  BaseView {

    /**
     * Sets place on UI
     * @param venues: List of places
     */
    fun showPlaces(venues: List<Venue>)

    /**
     * Shows loading
     */
    fun showLoading()

    /**
     * Stops loading
     */
    fun stopLoading()


    /**
     * Shows error
     * @param message: Error message to show on UI.
     */
    fun showError(message: String)

}
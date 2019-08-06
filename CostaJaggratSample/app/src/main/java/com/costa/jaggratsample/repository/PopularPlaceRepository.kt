package com.costa.jaggratsample.repository

import com.costa.jaggratsample.models.ResponseJSON

/**
 * Contract for getting Popular places
 */
interface PopularPlaceRepository {

    /**
     * Gets near by popular place from api.
     * @param clientId: Client id for API
     * @param clientSecret: Client Secret for API.
     * @param placeName: Name of the place.
     * @param callBack: call back for task is done
     */
    fun getNearByPlaces(clientId: String, clientSecret: String, placeName: String, callBack: RepositoryCallBack)


    interface RepositoryCallBack {
        /**
         * Called when task is completed
         * @param json: Response from API
         * @param error: Error from API
         */
        fun onResponse(json: ResponseJSON?, error: Throwable?)
    }
}
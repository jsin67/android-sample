package com.costa.jaggratsample.network.api

import com.costa.jaggratsample.models.ResponseJSON
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API End point for FourSquare
 */
interface PopularPlaceApi {

    @GET("venues/explore")
    fun fetchPopularPlace(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("near") name: String,
        @Query("v") version: String = "20190611"): Single<ResponseJSON>
}
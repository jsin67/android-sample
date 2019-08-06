package com.costa.jaggratsample.repository

import android.annotation.SuppressLint
import com.costa.jaggratsample.network.api.PopularPlaceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Contract implementation for repository
 */
class PopularPlaceRepositoryImpl constructor(val api : PopularPlaceApi) : PopularPlaceRepository {

    @SuppressLint("CheckResult")
    override fun getNearByPlaces(clientId: String, clientSecret: String, placeName: String, callBack: PopularPlaceRepository.RepositoryCallBack) {
        api.fetchPopularPlace(clientId, clientSecret, placeName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1, t2 -> callBack.onResponse(t1, t2)}
    }


}
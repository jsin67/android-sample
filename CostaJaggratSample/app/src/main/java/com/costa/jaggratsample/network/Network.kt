package com.costa.jaggratsample.network

import com.costa.jaggratsample.BASE_URL
import com.costa.jaggratsample.network.api.PopularPlaceApi
import com.costa.jaggratsample.repository.PopularPlaceRepository
import com.costa.jaggratsample.repository.PopularPlaceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providePostsRepo(restApi: PopularPlaceApi): PopularPlaceRepository {
        return PopularPlaceRepositoryImpl(restApi)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideRestApi(): PopularPlaceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        return retrofit.create(PopularPlaceApi::class.java)
    }
}
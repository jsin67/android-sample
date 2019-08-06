package com.costa.jaggratsample.presenter

import com.costa.jaggratsample.network.NetworkModule
import com.costa.jaggratsample.view.BaseView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface PresenterComponent {

    fun inject(searchPopularPlacePresenter: SearchPopularPlacePresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
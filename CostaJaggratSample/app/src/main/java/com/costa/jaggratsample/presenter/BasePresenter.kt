package com.costa.jaggratsample.presenter

import com.costa.jaggratsample.network.NetworkModule
import com.costa.jaggratsample.view.BaseView

/**
 * Base presenter for initialization for its implementation
 */
abstract class BasePresenter<V> (view: BaseView) {

    private val injector: PresenterComponent = DaggerPresenterComponent.builder().networkModule(NetworkModule).build()

    init {
        inject()
    }

    protected var view: BaseView = view

    /**
     * Injector for presenter.
     */
    private fun inject(){
        when(this){
            is SearchPopularPlacePresenter -> injector.inject(this)
        }
    }

}
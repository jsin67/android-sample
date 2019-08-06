package com.costa.jaggratsample

import com.costa.jaggratsample.models.*
import com.costa.jaggratsample.network.api.PopularPlaceApi
import com.costa.jaggratsample.presenter.SearchPopularPlacePresenter
import com.costa.jaggratsample.repository.PopularPlaceRepository
import com.costa.jaggratsample.view.SearchView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class SearchPopularPlacePresenterTest {
    private lateinit var repository: PopularPlaceRepository
    private lateinit var presenter: SearchPopularPlacePresenter
    private lateinit var view: SearchView

    @Before
    fun setUp(){

        repository = mock()
        view = mock()
        presenter = SearchPopularPlacePresenter(view)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun validate_search_params_with_all_invalid_param(){
        val clientId = " "
        val clientSecret = " "
        val placeName = " "

        presenter.getPopularPlaceDetails(clientId, clientSecret, placeName)

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view).showLoading()
        inOrder.verify(view).stopLoading()
        inOrder.verify(view).showError(any())
    }

    @Test
    fun validate_search_params_with_invalid_clientId_clientSecret_param(){
        val clientId = " "
        val clientSecret = " "
        val placeName = "london"

        presenter.getPopularPlaceDetails(clientId, clientSecret, placeName)

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view).showLoading()
        inOrder.verify(view).stopLoading()
        inOrder.verify(view).showError(any())
    }

    @Test
    fun validate_search_params_with_invalid_clientSecret_param(){
        val clientId = "AMSBDAXXSSAKSLASOOJSASOjo"
        val clientSecret = " "
        val placeName = "london"

        presenter.getPopularPlaceDetails(clientId, clientSecret, placeName)

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view).showLoading()
        inOrder.verify(view).stopLoading()
        inOrder.verify(view).showError(any())
    }


    @Test
    fun validate_loading_with_valid_param(){
        val clientId = "AMSBDAXXSSAKSLASOOJSASOjo"
        val clientSecret = "asdkjasdkjkj9832"
        val placeName = "london"

        presenter.getPopularPlaceDetails(clientId, clientSecret, placeName)

        verify(view).showLoading()
        verify(view).showPlaces(any())
    }


    private fun getMockValidResponse() : ResponseJSON {
        val venue1 = VenueResults(Venue("1", "Jaggrat"))
        val venue2 = VenueResults(Venue("2", "Singh"))
        val venue3 = VenueResults(Venue("3", "Bla bla"))
        val venue4 = VenueResults(Venue("4", "Cha cha"))
        val venue5 = VenueResults(Venue("5", "Ma ma"))
        val venueResults = listOf(venue1, venue2, venue3, venue4, venue5)
        return ResponseJSON(GroupResponse(arrayListOf(GroupItems(venueResults))))
    }

    private fun getMockVenueResponse(): List<Venue> {
        return arrayListOf(Venue("1", "Jaggrat"),
            Venue("2", "Singh"),
            Venue("3", "Bla bla"),
            Venue("4", "Cha cha"),
            Venue("5", "Ma ma"))
    }

    @Test
    fun validate_process_ui_with_valid_response(){
        presenter.onResponse(getMockValidResponse(), null)

        verify(view).stopLoading()
        verify(view).showPlaces(getMockVenueResponse())

    }

    @Test
    fun validate_process_ui_with_invalid_response(){

        presenter.onResponse(null, null)

        verify(view).stopLoading()
        verify(view, never()).showPlaces(getMockVenueResponse())

    }

    @Test
    fun validate_process_ui_with_error(){

        presenter.onResponse(null, Throwable("Sample"))

        verify(view).stopLoading()
        verify(view, never()).showPlaces(getMockVenueResponse())
        verify(view).showError(any())

    }

}
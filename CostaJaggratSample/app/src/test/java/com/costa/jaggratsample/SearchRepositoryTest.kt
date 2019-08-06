package com.costa.jaggratsample

import com.costa.jaggratsample.models.*
import com.costa.jaggratsample.network.api.PopularPlaceApi
import com.costa.jaggratsample.repository.PopularPlaceRepository
import com.costa.jaggratsample.repository.PopularPlaceRepositoryImpl
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnitRunner
import org.w3c.dom.Comment

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryTest {
    private lateinit var api: PopularPlaceApi
    private lateinit var repository: PopularPlaceRepository
    @Before
    fun setUp() {
        api = mock()
        repository = PopularPlaceRepositoryImpl(api)
    }

    @Test
    fun test_popular_api_result() {
        given(api.fetchPopularPlace(any(), any(), any())).willReturn(getMockValidResponse())

        whenever(api.fetchPopularPlace(any(), any(), any(), any())).thenAnswer { getMockValidResponse() }

    }

    private fun getMockValidResponse() : Single<ResponseJSON> {
        val venue1 = VenueResults(Venue("1", "Jaggrat"))
        val venue2 = VenueResults(Venue("2", "Singh"))
        val venue3 = VenueResults(Venue("3", "Bla bla"))
        val venue4 = VenueResults(Venue("4", "Cha cha"))
        val venue5 = VenueResults(Venue("5", "Ma ma"))
        val venueResults = listOf(venue1, venue2, venue3, venue4, venue5)
        return Single.just(ResponseJSON(GroupResponse(arrayListOf(GroupItems(venueResults)))))
    }
}
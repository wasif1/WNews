package com.wasif.search.repository

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Resource
import com.wasif.search.data.network.SearchApiService
import com.wasif.search.data.repository.SearchRepository
import com.wasif.search.utills.mockResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryTest {

    @Mock
    lateinit var searchApiService: SearchApiService

    @Test
    fun getSearch_caseLoading_Success() = runTest {
        doReturn(mockResponse).`when`(searchApiService).getSearch(API_KEY, "query")
        val repository = SearchRepository(searchApiService)
        repository.getSearch("query").test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Success(mockResponse), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(searchApiService, times(1)).getSearch(API_KEY, "query")
    }

    @Test
    fun getSearch_caseLoading_Failure() = runTest {
        val error = "Network Error"
        doThrow(RuntimeException(error)).`when`(searchApiService).getSearch(API_KEY, "query")
        val repository = SearchRepository(searchApiService)
        repository.getSearch("query").test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Error(error), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(searchApiService, times(1)).getSearch(API_KEY, "query")
    }
}
package com.wasif.topheadlines.repository

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.network.TopHeadlineApiService
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import com.wasif.topheadlines.utills.mockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
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


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesRepositoryTest {


    @Mock
    private lateinit var apiService: TopHeadlineApiService


    @Test
    fun getTopHeadlines_RepositoryEmitLoadingThenSucceed_Success() {
        runTest {
            doReturn(mockResponse)
                .`when`(apiService)
                .getTopHeadlines(COUNTRY, API_KEY)

            val topHeadlinesRepository = TopHeadlinesRepository(apiService)

            topHeadlinesRepository.getTopHeadlines(COUNTRY).test {
                assertEquals(awaitItem(), Resource.Loading)
                val success = awaitItem()
                assertEquals(success, Resource.Success(mockResponse))
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiService, times(1)).getTopHeadlines(COUNTRY, API_KEY)
        }
    }

    @Test
    fun getTopHeadlines_RepositoryEmitLoadingThenFail_Failure() {
        runTest {
            val errorMessage = "Error"

            doThrow(RuntimeException(errorMessage))
                .`when`(apiService)
                .getTopHeadlines(COUNTRY, API_KEY)

            val topHeadlinesRepository = TopHeadlinesRepository(apiService)
            topHeadlinesRepository.getTopHeadlines(COUNTRY).test {
                assertEquals(awaitItem(), Resource.Loading)
                val error = awaitItem()
                assertEquals(error, Resource.Error(errorMessage))
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiService, times(1)).getTopHeadlines(COUNTRY, API_KEY)
        }
    }
}
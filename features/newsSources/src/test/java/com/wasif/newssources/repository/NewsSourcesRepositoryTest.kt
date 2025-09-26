package com.wasif.newssources.repository

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Resource
import com.wasif.newssources.data.network.NewsSourcesApiService
import com.wasif.newssources.data.repository.NewsSourcesRepository
import com.wasif.newssources.utills.mockResponse
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
class NewsSourcesRepositoryTest {

    @Mock
    private lateinit var apiService: NewsSourcesApiService

    @Test
    fun getNewsSources_caseLoadingSuccess_Success() {
        runTest {
            doReturn(mockResponse)
                .`when`(apiService)
                .getNewsSources(API_KEY)

            val repository = NewsSourcesRepository(apiService)
            repository.getNewsSources().test {
                assertEquals(awaitItem(), Resource.Loading)
                assertEquals(awaitItem(), Resource.Success(mockResponse))
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiService, times(1)).getNewsSources(API_KEY)
        }
    }

    @Test
    fun getNewsSources_caseLoadingFailure_Failure() {
        runTest {
            val errorMessage = "Network Error"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiService)
                .getNewsSources(API_KEY)

            val repository = NewsSourcesRepository(apiService)
            repository.getNewsSources().test {
                assertEquals(awaitItem(), Resource.Loading)
                assertEquals(awaitItem(), Resource.Error(errorMessage))
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiService, times(1)).getNewsSources(API_KEY)
        }
    }
}
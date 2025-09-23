package com.wasif.topheadlines.topHeadlines

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.API_KEY
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.models.ArticlesItem
import com.wasif.topheadlines.data.models.TopHeadlines
import com.wasif.topheadlines.data.network.TopHeadlineApiService
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class TopHeadlinesRepositoryTest {

    @Mock
    lateinit var topHeadlineApiService: TopHeadlineApiService

    private lateinit var topHeadlinesRepository: TopHeadlinesRepository

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        topHeadlinesRepository = TopHeadlinesRepository(topHeadlineApiService)
    }

    @Test
    fun getTopHeadlines_RepositoryEmitLoadingThenSucceed_Success() = runTest {
        val mockResponse = TopHeadlines(
            status = "ok",
            totalResults = 1,
            articles = listOf(ArticlesItem(title = "Test headline"))
        )
        Mockito.`when`(topHeadlineApiService.getTopHeadlines(country = COUNTRY, apiKey = API_KEY))
            .thenReturn(mockResponse)
        topHeadlinesRepository.getTopHeadlines(COUNTRY).test {
            assert(awaitItem() is Resource.Loading)
            val success = awaitItem()
            assert(success is Resource.Success && success.data == mockResponse)
            awaitComplete()
        }
        verify(topHeadlineApiService, times(1)).getTopHeadlines(country = COUNTRY, apiKey = API_KEY)
    }

    @Test
    fun getTopHeadlines_RepositoryEmitLoadingThenFail_Failure() = runTest {
        val error = "error"
        Mockito.`when`(topHeadlineApiService.getTopHeadlines(country = COUNTRY, apiKey = API_KEY))
            .thenThrow(RuntimeException(error))
        topHeadlinesRepository.getTopHeadlines(COUNTRY).test {
            assert(awaitItem() is Resource.Loading)
            val failure = awaitItem()
            assert(failure is Resource.Error && failure.message == error)
            awaitComplete()
        }
        verify(topHeadlineApiService, times(1)).getTopHeadlines(country = COUNTRY, apiKey = API_KEY)
    }
}
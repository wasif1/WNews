package com.wasif.topheadlines.topHeadlines

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.models.TopHeadlines
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TopHeadlinesUseCaseTest {

    @Mock
    lateinit var repository: TopHeadlinesRepository

    lateinit var topHeadlinesUseCase: TopHeadlinesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        topHeadlinesUseCase = TopHeadlinesUseCase(repository)
    }

    @Test
    fun invoke_callRepository_getResult() = runTest {
        val mockResponse = TopHeadlines(status = "ok", totalResults = 0, articles = emptyList())

        Mockito.`when`(repository.getTopHeadlines(COUNTRY))
            .thenReturn(flowOf(Resource.Success(mockResponse)))

        topHeadlinesUseCase(COUNTRY).test {
            val result = awaitItem()
            assert(result is Resource.Success && result.data == mockResponse)
            awaitComplete()
        }
    }
}
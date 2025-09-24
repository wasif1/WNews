package com.wasif.topheadlines.usecases

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.repository.TopHeadlinesRepository
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import com.wasif.topheadlines.utills.mockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesUseCaseTest {

    @Mock
    lateinit var repository: TopHeadlinesRepository

    @Test
    fun invoke_callRepository_getSuccessResult() = runTest {
        doReturn(flowOf(Resource.Success(mockResponse)))
            .`when`(repository)
            .getTopHeadlines(COUNTRY)

        val topHeadlinesUseCase = TopHeadlinesUseCase(repository)
        topHeadlinesUseCase.invoke(COUNTRY).test {
            val result = awaitItem()
            assertEquals(result, Resource.Success(mockResponse))
            cancelAndIgnoreRemainingEvents()
        }
        verify(repository, times(1)).getTopHeadlines(COUNTRY)
    }
}
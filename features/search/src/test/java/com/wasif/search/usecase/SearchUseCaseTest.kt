package com.wasif.search.usecase

import app.cash.turbine.test
import com.wasif.search.data.repository.SearchRepository
import com.wasif.search.domain.usecases.SearchUseCase
import com.wasif.search.utills.mockResponse
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

@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {

    @Mock
    lateinit var searchRepository: SearchRepository

    @Test
    fun invoke_case() = runTest {
        doReturn(flowOf(mockResponse)).`when`(searchRepository).getSearch("query")
        val useCase = SearchUseCase(searchRepository)
        useCase.invoke("query").test {
            assertEquals(mockResponse, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(searchRepository, times(1)).getSearch("query")
    }
}
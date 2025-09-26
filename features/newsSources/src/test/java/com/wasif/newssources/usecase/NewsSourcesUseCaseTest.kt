package com.wasif.newssources.usecase

import app.cash.turbine.test
import com.wasif.newssources.data.repository.NewsSourcesRepository
import com.wasif.newssources.domain.NewsSourcesUseCase
import com.wasif.newssources.utills.mockResponse
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
class NewsSourcesUseCaseTest {

    @Mock
    lateinit var repository: NewsSourcesRepository

    @Test
    fun invoke_test() {
        runTest {
            doReturn(flowOf(mockResponse))
                .`when`(repository)
                .getNewsSources()
            val useCase = NewsSourcesUseCase(repository)
            useCase.invoke().test {
                assertEquals(awaitItem(), mockResponse)
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository, times(1)).getNewsSources()
        }
    }
}
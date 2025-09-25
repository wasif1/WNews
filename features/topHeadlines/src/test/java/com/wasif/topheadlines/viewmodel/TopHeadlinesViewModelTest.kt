package com.wasif.topheadlines.viewmodel

import app.cash.turbine.test
import com.wasif.core.data.models.UiState
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import com.wasif.topheadlines.presentation.viewmodel.TopHeadlinesViewModel
import com.wasif.topheadlines.utills.TestDefaultDispatcher
import com.wasif.topheadlines.utills.mockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesViewModelTest {

    @Mock
    lateinit var topHeadlinesUseCase: TopHeadlinesUseCase

    private lateinit var dispatcher: TestDefaultDispatcher

    @Before
    fun setup() {
        dispatcher = TestDefaultDispatcher()
    }

    @Test
    fun fetchTopHeadlines_callUseCase_LoadingResult() = runTest {

        doReturn(flowOf(Resource.Loading)).`when`(topHeadlinesUseCase).invoke(COUNTRY)

        val topHeadlinesViewModel = TopHeadlinesViewModel(topHeadlinesUseCase, dispatcher)
        topHeadlinesViewModel.fetchTopHeadlines(COUNTRY)
        topHeadlinesViewModel.uiState.test {
            assertEquals(awaitItem(), UiState(isLoading = true, data = null, error = null))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchTopHeadlines_callUseCase_successResult() = runTest {

        doReturn(flowOf(Resource.Success(mockResponse))).`when`(topHeadlinesUseCase).invoke(COUNTRY)

        val topHeadlinesViewModel = TopHeadlinesViewModel(topHeadlinesUseCase, dispatcher)
        topHeadlinesViewModel.fetchTopHeadlines(COUNTRY)
        topHeadlinesViewModel.uiState.test {
            assertEquals(
                UiState(isLoading = false, data = mockResponse.articles, error = null), awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchTopHeadlines_callUseCase_ErrorResult() = runTest {
        val errorMessage = "Network Error"
        doReturn(flowOf(Resource.Error(errorMessage))).`when`(topHeadlinesUseCase).invoke(COUNTRY)

        val topHeadlinesViewModel = TopHeadlinesViewModel(topHeadlinesUseCase, dispatcher)
        topHeadlinesViewModel.fetchTopHeadlines(COUNTRY)
        topHeadlinesViewModel.uiState.test {
            assertEquals(UiState(isLoading = false, data = null, error = errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
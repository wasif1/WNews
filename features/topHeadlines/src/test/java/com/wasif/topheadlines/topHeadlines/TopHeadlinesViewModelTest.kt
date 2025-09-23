package com.wasif.topheadlines.topHeadlines

import app.cash.turbine.test
import com.wasif.core.utills.Constants.Companion.COUNTRY
import com.wasif.core.utills.Resource
import com.wasif.topheadlines.data.models.ArticlesItem
import com.wasif.topheadlines.data.models.TopHeadlines
import com.wasif.topheadlines.domain.TopHeadlinesUseCase
import com.wasif.topheadlines.presentation.viewmodel.TopHeadlinesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TopHeadlinesViewModelTest {

    @Mock
    lateinit var topHeadlinesUseCase: TopHeadlinesUseCase

    lateinit var topHeadlinesViewModel: TopHeadlinesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(StandardTestDispatcher())
        topHeadlinesViewModel = TopHeadlinesViewModel(topHeadlinesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchTopHeadlines_callUseCase_successResult() = runTest {
        val mockArticles = listOf(ArticlesItem(title = "Test headline"))
        val mockResponse = TopHeadlines(status = "ok", totalResults = 1, articles = mockArticles)

        Mockito.`when`(topHeadlinesUseCase(COUNTRY))
            .thenReturn(flowOf(Resource.Success(mockResponse)))

        topHeadlinesViewModel.fetchTopHeadlines(COUNTRY)

        topHeadlinesViewModel.uiState.test {
            val state = awaitItem()
            assert(state.data == mockArticles && !state.isLoading && state.error == null)
        }
    }
}
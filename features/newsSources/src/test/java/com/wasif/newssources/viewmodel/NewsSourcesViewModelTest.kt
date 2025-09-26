package com.wasif.newssources.viewmodel

import app.cash.turbine.test
import com.wasif.core.data.models.UiState
import com.wasif.core.utills.Resource
import com.wasif.newssources.domain.NewsSourcesUseCase
import com.wasif.newssources.presentation.viewmodel.NewsSourcesViewModel
import com.wasif.newssources.utills.TestDispatcher
import com.wasif.newssources.utills.mockResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class NewsSourcesViewModelTest {

    @Mock
    lateinit var useCase: NewsSourcesUseCase
    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setup() {
        dispatcher = TestDispatcher()
    }

    @Test
    fun fetchNewsSources_CaseLoading_Loading() {
        runTest {
            doReturn(flowOf(Resource.Loading)).`when`(useCase).invoke()
            val viewModel = NewsSourcesViewModel(useCase, dispatcher)
            viewModel.fetchNewsSources()
            viewModel.uiState.test {
                assertEquals(UiState(isLoading = true, error = null, data = null), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke()
        }
    }

    @Test
    fun fetchNewsSources_CaseError_Failure() {
        runTest {
            val errorMessage = "Error occurred"
            doReturn(flowOf(Resource.Error(errorMessage))).`when`(useCase).invoke()
            val viewModel = NewsSourcesViewModel(useCase, dispatcher)
            viewModel.fetchNewsSources()
            viewModel.uiState.test {
                assertEquals(
                    UiState(isLoading = false, error = errorMessage, data = null), awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke()
        }
    }

    @Test
    fun fetchNewsSources_CaseSuccess_Success() {
        runTest {
            doReturn(flowOf(Resource.Success(mockResponse))).`when`(useCase).invoke()
            val viewModel = NewsSourcesViewModel(useCase, dispatcher)
            viewModel.fetchNewsSources()
            viewModel.uiState.test {
                assertEquals(
                    UiState(isLoading = false, error = null, data = mockResponse.sources),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCase, times(1)).invoke()
        }
    }
}
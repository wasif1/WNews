package com.wasif.countries.viewmodel

import app.cash.turbine.test
import com.wasif.core.data.models.UiState
import com.wasif.core.utills.Resource
import com.wasif.countries.domain.usecase.CountriesUseCase
import com.wasif.countries.presentation.viewmodel.CountriesViewModel
import com.wasif.languages.utills.TestDefaultDispatcher
import com.wasif.countries.utills.mockResponse
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
class CountryViewModelTest {

    @Mock
    lateinit var countryUseCase: CountriesUseCase
    lateinit var testDispatcher: TestDefaultDispatcher

    @Before
    fun setUp() {
        testDispatcher = TestDefaultDispatcher()
    }

    @Test
    fun fetchCountries_caseLoading() = runTest {
        doReturn(flowOf(Resource.Loading)).`when`(countryUseCase).invoke()
        val viewModel = CountriesViewModel(countryUseCase, testDispatcher)
        viewModel.fetchCountries()
        viewModel.uiState.test {
            assertEquals(UiState(isLoading = true, data = null, error = null), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(countryUseCase, times(1)).invoke()
    }

    @Test
    fun fetchCountries_caseSuccess() = runTest {
        doReturn(flowOf(Resource.Success(mockResponse))).`when`(countryUseCase).invoke()
        val viewModel = CountriesViewModel(countryUseCase, testDispatcher)
        viewModel.fetchCountries()
        viewModel.uiState.test {
            assertEquals(UiState(isLoading = false, data = mockResponse, error = null), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(countryUseCase, times(1)).invoke()
    }

    @Test
    fun fetchCountries_caseFailure() = runTest {
        val errorMessage = "Error occurred"
        doReturn(flowOf(Resource.Error(errorMessage))).`when`(countryUseCase).invoke()
        val viewModel = CountriesViewModel(countryUseCase, testDispatcher)
        viewModel.fetchCountries()
        viewModel.uiState.test {
            assertEquals(UiState(isLoading = false, data = null, error = errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(countryUseCase, times(1)).invoke()
    }
}
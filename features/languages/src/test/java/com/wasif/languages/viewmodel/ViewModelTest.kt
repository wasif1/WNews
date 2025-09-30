package com.wasif.languages.viewmodel

import app.cash.turbine.test
import com.wasif.core.utills.Resource
import com.wasif.languages.domain.usecase.LanguagesUseCase
import com.wasif.languages.presentation.viewmodel.LanguagesViewModel
import com.wasif.languages.utills.TestDefaultDispatcher
import com.wasif.languages.utills.mockResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @Mock
    private lateinit var useCase: LanguagesUseCase
    private lateinit var testDispatcher: TestDefaultDispatcher

    @Before
    fun setup() {
        testDispatcher = TestDefaultDispatcher()
    }

    @Test
    fun fetchLanguages_LoadingCase_Loading() = runTest {
        doReturn(flowOf(Resource.Loading)).`when`(useCase).invoke()
        val viewModel = LanguagesViewModel(useCase, testDispatcher)
        viewModel.fetchLanguages()
        useCase.invoke().test {
            assertEquals(Resource.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchLanguages_SuccessCase_Success() = runTest {
        doReturn(flowOf(Resource.Success(mockResponse))).`when`(useCase).invoke()
        val viewModel = LanguagesViewModel(useCase, testDispatcher)
        viewModel.fetchLanguages()
        useCase.invoke().test {
            assertEquals(Resource.Success(mockResponse), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchLanguages_FailureCase_Failure() = runTest {
        val errorMessage = "Error occurred"
        doReturn(flowOf(Resource.Error(errorMessage))).`when`(useCase).invoke()
        val viewModel = LanguagesViewModel(useCase, testDispatcher)
        viewModel.fetchLanguages()
        useCase.invoke().test {
            assertEquals(Resource.Error(errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
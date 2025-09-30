package com.wasif.languages.usecase

import app.cash.turbine.test
import com.wasif.languages.domain.repository.LanguageRepository
import com.wasif.languages.domain.usecase.LanguagesUseCase
import com.wasif.languages.utills.mockResponse
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
class LanguageUseCaseTest {

    @Mock
    private lateinit var repository: LanguageRepository

    @Test
    fun invoke_case() {
        runTest {
            doReturn(flowOf(mockResponse)).`when`(repository).getLanguages()
            val useCase = LanguagesUseCase(repository)
            useCase.invoke().test {
                assertEquals(mockResponse, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository, times(1)).getLanguages()
        }
    }
}
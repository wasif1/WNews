package com.wasif.languages.repository

import app.cash.turbine.test
import com.wasif.core.utills.Resource
import com.wasif.languages.data.repository.LanguageRepositoryImp
import com.wasif.languages.data.repository.sources.DataSource
import com.wasif.languages.utills.mockResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class LanguageRepositoryTest {

    @Mock
    private lateinit var dataSource: DataSource
    private lateinit var repository: LanguageRepositoryImp

    @Before
    fun setUp() {
        repository = LanguageRepositoryImp(setOf(dataSource))
    }

    @Test
    fun getLanguages_SuccessCase_Success() {
        runTest {
            doReturn(mockResponse).`when`(dataSource).getLanguages()
            repository.getLanguages().test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Success(mockResponse), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(dataSource, times(1)).getLanguages()
        }
    }

    @Test
    fun getLanguages_FailureCase_Failure() {
        runTest {
            val errorMessage = "No data available"
            doThrow(RuntimeException(errorMessage)).`when`(dataSource).getLanguages()
            repository.getLanguages().test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Error(errorMessage), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(dataSource, times(1)).getLanguages()
        }
    }
}
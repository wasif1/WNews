package com.wasif.languages.repository

import app.cash.turbine.test
import com.wasif.core.utills.Resource
import com.wasif.languages.data.repository.LanguageRepositoryImp
import com.wasif.languages.data.repository.sources.DataSource
import com.wasif.languages.domain.repository.LanguageRepository
import com.wasif.languages.utills.mockResponse
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class LanguageRepository {

    @Mock
    private lateinit var dataSource: DataSource

    @Mock
    private lateinit var repository: LanguageRepository


    @Test
    suspend fun getLanguages_SuccessCase_Success() {// not working
        doReturn(flowOf(mockResponse)).`when`(repository).getLanguages()
        val repository = LanguageRepositoryImp(setOf(dataSource))
        repository.getLanguages().test {
            assertEquals(awaitItem(), Resource.Loading)
            val success = awaitItem()
            assertEquals(success, Resource.Success(mockResponse))
            cancelAndIgnoreRemainingEvents()
        }
        verify(dataSource, times(1)).getLanguages()
    }
}
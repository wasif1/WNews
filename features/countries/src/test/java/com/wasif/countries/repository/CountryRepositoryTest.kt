package com.wasif.countries.repository

import app.cash.turbine.test
import com.wasif.core.utills.Resource
import com.wasif.countries.data.repository.CountryRepositoryImp
import com.wasif.countries.data.repository.sources.DataSource
import com.wasif.countries.utills.mockResponse
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
class CountryRepositoryTest {

    @Mock
    lateinit var source: DataSource
    lateinit var countryRepository: CountryRepositoryImp

    @Before
    fun setUp() {
        countryRepository = CountryRepositoryImp(setOf(source))
    }

    @Test
    fun getCountries_caseLoading_Success() {
        runTest {
            doReturn(mockResponse).`when`(source).getCountries()
            countryRepository.getCountries().test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Success(mockResponse), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(source, times(1)).getCountries()
        }
    }

    @Test
    fun getCountries_caseLoading_Failed() {
        runTest {
            val errorMessage = "No data available"
            doThrow(RuntimeException(errorMessage)).`when`(source).getCountries()
            countryRepository.getCountries().test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Error(errorMessage), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(source, times(1)).getCountries()
        }
    }
}
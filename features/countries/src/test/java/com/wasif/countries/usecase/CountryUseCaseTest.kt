package com.wasif.countries.usecase

import app.cash.turbine.test
import com.wasif.countries.domain.repository.CountryRepository
import com.wasif.countries.utills.mockResponse
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
class CountryUseCaseTest {

    @Mock
    lateinit var countryRepository: CountryRepository

    @Test
    fun getCountries_Invoke() = runTest {
        doReturn(flowOf(mockResponse)).`when`(countryRepository).getCountries()
        countryRepository.getCountries().test {
            assertEquals(mockResponse, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(countryRepository, times(1)).getCountries()
    }
}
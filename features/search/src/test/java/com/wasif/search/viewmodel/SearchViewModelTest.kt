package com.wasif.search.viewmodel

import com.wasif.search.domain.usecases.SearchUseCase
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Mock
    lateinit var searchUseCase: SearchUseCase

}
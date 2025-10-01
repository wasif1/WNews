package com.wasif.countries.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wasif.core.data.models.UiState
import com.wasif.countries.R
import com.wasif.countries.presentation.ui.CountriesScreen
import com.wasif.countries.utills.mockResponse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountriesActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun countryActivity_CaseLoading_View() {
        rule.setContent {
            CountriesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = true),
                onClick = { }
            )
        }

        rule.onNodeWithContentDescription(rule.activity.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun countryActivity_CaseError_View() {
        rule.setContent {
            CountriesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = "Network Error"),
                onClick = { }
            )
        }

        rule.onNodeWithText("Network Error")
            .assertExists()
    }

    @Test
    fun countryActivity_CaseSuccess_View() {
        rule.setContent {
            CountriesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = null, data = mockResponse),
                onClick = { }
            )
        }

        rule.onNodeWithText(mockResponse[0].name ?: "")
            .assertExists()
            .assertHasClickAction()

        rule.onNode(hasScrollToNodeAction())
            .performScrollToNode(hasText(mockResponse[5].name ?: ""))

        rule.onNodeWithText(mockResponse[5].name ?: "")
            .assertExists()
            .assertHasClickAction()
    }

}
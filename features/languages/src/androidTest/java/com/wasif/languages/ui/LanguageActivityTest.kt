package com.wasif.languages.ui

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
import com.wasif.languages.R
import com.wasif.languages.presentation.ui.LanguagesScreen
import com.wasif.languages.utills.mockResponse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LanguageActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun languagesScreen_LoadingState_isLoadingShowing() {
        rule.setContent {
            LanguagesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = true),
                onLanguageClick = { }
            )
        }

        rule.onNodeWithContentDescription(rule.activity.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun languagesScreen_ErrorState_isErrorShowing() {
        rule.setContent {
            LanguagesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = "network error"),
                onLanguageClick = { }
            )
        }

        rule.onNodeWithText("network error")
            .assertExists()
    }

    @Test
    fun languagesScreen_SuccessState_isListShowing() {
        rule.setContent {
            LanguagesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = null, data = mockResponse),
                onLanguageClick = { }
            )
        }

        rule.onNodeWithText(mockResponse[0].name ?: "", substring = true)
            .assertExists()
            .assertHasClickAction()

        rule.onNode(hasScrollToNodeAction())
            .performScrollToNode(hasText(mockResponse.get(0).name ?: ""))

        rule.onNodeWithText(mockResponse[5].name ?: "", substring = true)
            .assertExists()
            .assertHasClickAction()

    }
}
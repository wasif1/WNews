package com.wasif.newssources

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.wasif.core.data.models.UiState
import com.wasif.newssources.presentation.ui.NewSourcesScreen
import com.wasif.newssources.utills.mockResponse
import org.junit.Rule
import org.junit.Test


class NewsSourcesActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun newSourcesScreen_CaseLoading_Loading() {
        rule.setContent {
            NewSourcesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = true, error = null, data = emptyList()),
                onArticleClick = {}
            )
        }

        rule.onNodeWithContentDescription(rule.activity.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun newSourcesScreen_CaseError_Failure() {
        rule.setContent {
            NewSourcesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = "network error", data = emptyList()),
                onArticleClick = {}
            )
        }

        rule.onNodeWithText("network error")
            .assertExists()
    }

    @Test
    fun newSourcesScreen_CaseSuccess_Success() {
        rule.setContent {
            NewSourcesScreen(
                modifier = Modifier,
                uiState = UiState(isLoading = false, error = null, data = mockResponse.sources),
                onArticleClick = {}
            )
        }

        rule.onNodeWithText(
            mockResponse.sources?.get(0)?.name ?: "",
            substring = true
        ).assertExists().assertHasClickAction()

        rule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    mockResponse.sources?.get(6)?.name ?: "",
                    substring = true
                )
            )

        rule.onNodeWithText(
            mockResponse.sources?.get(6)?.name ?: "",
            substring = true
        ).assertExists().assertHasClickAction()
    }
}
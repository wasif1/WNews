package com.wasif.topheadlines.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wasif.core.data.models.UiState
import com.wasif.topheadlines.R
import com.wasif.topheadlines.presentation.ui.HeadlinesScreen
import com.wasif.topheadlines.utills.mockResponse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopHeadlinesActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        rule.setContent {
            HeadlinesScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = UiState(isLoading = true),
                onArticleClick = {},
            )
        }

        rule.onAllNodesWithContentDescription(rule.activity.getString(R.string.loading))
            .assertCountEquals(1)
    }

    @Test
    fun error_whenUiStateIsError_isShown() {
        rule.setContent {
            HeadlinesScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = UiState(isLoading = false, error = "Error"),
                onArticleClick = {},
            )
        }

        rule.onNodeWithText("Error").assertExists()
    }

    @Test
    fun success_whenUiStateIsSuccess_isShown() {
        rule.setContent {
            HeadlinesScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = UiState(isLoading = false, data = mockResponse.articles),
                onArticleClick = {},
            )
        }

        mockResponse.articles?.get(0)?.let {
            it.title?.let { it1 ->
                rule.onNodeWithText(it1, substring = true)
                    .assertExists()
                    .assertHasClickAction()
            }
        }

        mockResponse.articles?.get(5)?.title?.let {
            hasText(
                it,
                substring = true
            )
        }?.let {
            rule.onNode(hasScrollToNodeAction())
                .performScrollToNode(
                    it
                )
        }

        mockResponse.articles?.get(5)?.let {
            it.title?.let { it1 ->
                rule.onNodeWithText(it1, substring = true)
                    .assertExists()
                    .assertHasClickAction()
            }
        }
    }
}
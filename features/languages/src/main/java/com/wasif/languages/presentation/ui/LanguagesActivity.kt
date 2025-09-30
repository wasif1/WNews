package com.wasif.languages.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.NewsApplication
import com.wasif.core.data.models.UiState
import com.wasif.core.theme.WNewsTheme
import com.wasif.languages.R
import com.wasif.languages.data.models.Language
import com.wasif.languages.di.components.DaggerLanguageComponent
import com.wasif.languages.di.modules.LanguageModule
import com.wasif.languages.presentation.viewmodel.LanguagesViewModel
import com.wasif.topheadlines.presentation.ui.TopHeadlinesActivity
import javax.inject.Inject


class LanguagesActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LanguagesActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LanguagesViewModel by viewModels { viewModelFactory }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
        enableEdgeToEdge()
        setContent {
            WNewsTheme {
                LaunchedEffect(Unit) {
                    viewModel.fetchLanguages()
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Languages") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }) { innerPadding ->

                    val uiState by viewModel.uiState.collectAsState()
                    LanguagesScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState,
                        onLanguageClick = { data ->
                            TopHeadlinesActivity.newIntent(this, data.code?.lowercase()).also {
                                startActivity(it)
                            }
                        }
                    )
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerLanguageComponent.builder()
            .coreComponent((application as NewsApplication).coreComponent)
            .languageModule(LanguageModule(this))
            .build().inject(this)
    }
}

@Composable
fun LanguagesScreen(
    modifier: Modifier,
    uiState: UiState<List<Language>>,
    onLanguageClick: (Language) -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val loading = stringResource(id = R.string.loading)
                CircularProgressIndicator(modifier.semantics {
                    contentDescription = loading
                })
            }
        }

        uiState.error != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.error ?: "Unknown Error",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.data ?: emptyList()) { data ->
                    LanguageItem(data, onClick = { onLanguageClick(data) })
                }
            }
        }
    }
}


@Composable
fun LanguageItem(lang: Language, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = lang.name ?: "No Title",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
package com.wasif.countries.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wasif.core.NewsApplication
import com.wasif.core.theme.WNewsTheme
import com.wasif.countries.di.component.DaggerCountriesComponent
import com.wasif.countries.di.module.CountriesModule


class CountriesActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CountriesActivity::class.java)
        }
    }

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

//    private val viewModel: NewsSourcesViewModel by viewModels { viewModelFactory }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
        enableEdgeToEdge()
        setContent {
            WNewsTheme {
                LaunchedEffect(Unit) {
//                    viewModel.fetchNewsSources()
                }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Countries") },
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
                    Text("Hello", modifier = Modifier.padding(innerPadding))
//                    val uiState by viewModel.uiState.collectAsState()
//                    NewSourcesScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        uiState = uiState,
//                        onArticleClick = { source ->
//                            this.openUrl(source.url ?: "")
//                        }
//                    )
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerCountriesComponent.builder()
            .coreComponent((application as NewsApplication).coreComponent)
            .countriesModule(CountriesModule(this))
            .build().inject(this)
    }
}

//@Composable
//fun NewSourcesScreen(
//    modifier: Modifier,
//    uiState: UiState<List<SourcesItem>>,
//    onArticleClick: (SourcesItem) -> Unit
//) {
//    when {
//        uiState.isLoading -> {
//            Box(
//                modifier = modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//
//        uiState.error != null -> {
//            Box(
//                modifier = modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = uiState.error ?: "Unknown Error",
//                    color = Color.Red,
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//
//        else -> {
//            LazyColumn(
//                modifier = modifier.fillMaxSize(),
//                contentPadding = PaddingValues(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(uiState.data ?: emptyList()) { article ->
//                    SourcesItem(article, onClick = { onArticleClick(article) })
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun SourcesItem(article: SourcesItem, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() },
//        elevation = CardDefaults.cardElevation(4.dp),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp)
//        ) {
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                text = article.name ?: "No Title",
//                style = MaterialTheme.typography.titleMedium,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
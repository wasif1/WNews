package com.wasif.search.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.wasif.core.NewsApplication
import com.wasif.core.theme.WNewsTheme
import com.wasif.core.utills.Resource
import com.wasif.search.di.component.DaggerSearchComponent
import com.wasif.search.di.module.SearchModule
import com.wasif.search.presentation.viewmodel.SearchViewModel
import javax.inject.Inject


class SearchActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
        enableEdgeToEdge()
        setContent {
            WNewsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Search") },
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
                    M3SearchBar(viewModel, innerPadding)
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerSearchComponent.builder()
            .coreComponent((application as NewsApplication).coreComponent)
            .searchModule(SearchModule(this))
            .build().inject(this)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3SearchBar(viewModel: SearchViewModel, innerPadding: PaddingValues) {
    val query by viewModel.query.collectAsState()
    val results by viewModel.searchResults.collectAsState()
    var active by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            query = query,
            onQueryChange = { viewModel.onQueryChange(it) },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Search...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (active) {
                    IconButton(onClick = {
                        active = false
                        viewModel.onQueryChange("")
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            }
        ) {
            when (val result = results) {
                is Resource.Loading ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (query.isNotEmpty())
                            CircularProgressIndicator()
                    }

                is Resource.Success -> {
                    LazyColumn {
                        result.data.articles?.let {
                            items(it) { item ->

                                Card(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                        .clickable {

                                        },
                                    elevation = CardDefaults.cardElevation(4.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp)
                                    ) {

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = item?.title ?: "No Title",
                                            style = MaterialTheme.typography.titleMedium,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                    }
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> Text("Error: ${result.message}")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
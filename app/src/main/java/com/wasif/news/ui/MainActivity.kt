package com.wasif.news.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wasif.core.theme.WNewsTheme
import com.wasif.core.utills.Extensions.showToast
import com.wasif.countries.presentation.ui.CountriesActivity
import com.wasif.languages.presentation.ui.LanguagesActivity
import com.wasif.newssources.presentation.ui.NewsSourcesActivity
import com.wasif.search.presentation.ui.SearchActivity
import com.wasif.topheadlines.presentation.ui.TopHeadlinesActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        TextBox(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .background(color = Color.Red)
                                .padding(24.dp)
                                .align(Alignment.CenterHorizontally),
                            name = "Top Headlines",
                        )

                        TextBox(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .background(color = Color.Blue)
                                .padding(24.dp)
                                .align(Alignment.CenterHorizontally),
                            name = "News Sources",
                        )

                        TextBox(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .background(color = Color.Black)
                                .padding(24.dp)
                                .align(Alignment.CenterHorizontally),
                            name = "Countries",
                        )

                        TextBox(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .background(color = Color.DarkGray)
                                .padding(24.dp)
                                .align(Alignment.CenterHorizontally),
                            name = "Languages",
                        )

                        TextBox(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .background(color = Color.Gray)
                                .padding(24.dp)
                                .align(Alignment.CenterHorizontally),
                            name = "Search",
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun TextBox(name: String, modifier: Modifier = Modifier) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = modifier
                .width(150.dp)
                .clickable {
                    when (name) {
                        "Top Headlines" -> startActivity(
                            TopHeadlinesActivity.newIntent(this)
                        )

                        "News Sources" -> startActivity(
                            NewsSourcesActivity.newIntent(this)
                        )

                        "Countries" -> startActivity(
                            CountriesActivity.newIntent(this)
                        )

                        "Languages" -> startActivity(
                            LanguagesActivity.newIntent(this)
                        )

                        "Search" -> startActivity(
                            SearchActivity.newIntent(this)
                        )

                        else -> {
                            this.showToast("$name Screen is in Progress")
                        }
                    }
                }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
package com.wasif.topheadlines.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wasif.core.NewsApplication
import com.wasif.core.presentation.ui.theme.WNewsTheme
import com.wasif.topheadlines.di.component.DaggerTopHeadlinesComponent
import com.wasif.topheadlines.di.component.TopHeadlinesComponent
import com.wasif.topheadlines.di.module.TopHeadlineModule


class TopHeadlinesActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TopHeadlinesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
        enableEdgeToEdge()
        setContent {
            WNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Top Headlines",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerTopHeadlinesComponent.builder()
            .topHeadlineModule(TopHeadlineModule(this))
            .coreComponent((application as NewsApplication).coreComponent)
            .build().inject(this)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WNewsTheme {
        Greeting("Top Headlines")
    }
}
package com.andrewkingmarshall.apollostarwars.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.andrewkingmarshall.apollostarwars.ui.theme.ApolloStarWarsTheme
import com.andrewkingmarshall.apollostarwars.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApolloStarWarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting { mainViewModel.onButtonClicked() }
                }
            }
        }
    }
}

@Composable
fun Greeting(clickListener: () -> Unit = { Timber.d("Implement Me!") }) {

    listOf(Color.Red, Color.Green)

    Button(
        onClick = clickListener,
        colors = ButtonDefaults.buttonColors(backgroundColor = listOf(Color.Red, Color.Green, Color.Cyan).shuffled().first() )
    ) {
        Text(text = "Click Me!")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApolloStarWarsTheme {
        Greeting()
    }
}
package app.daily.scoop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.daily.scoop.ui.theme.DailyScoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DailyScoopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen()
                }
            }
        }
    }
}

@Composable
internal fun NewsScreen(
    viewModel: MainVM = hiltViewModel(),
) {
    val newsState: NewsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    when (newsState) {
        is NewsUiState.Loading -> Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
        is NewsUiState.Success -> Toast.makeText(context, "SUCCESS...", Toast.LENGTH_SHORT).show()
        is NewsUiState.Error -> Toast.makeText(
            context,
            "ERROR: ${(newsState as NewsUiState.Error)}",
            Toast.LENGTH_SHORT
        ).show()
    }

    Greeting("Android")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailyScoopTheme {
        Greeting("Android")
    }
}

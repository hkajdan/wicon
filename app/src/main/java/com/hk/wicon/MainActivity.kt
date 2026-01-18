package com.hk.wicon

import android.os.Bundle
import android.view.InputDevice
import android.view.KeyEvent
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
import com.hk.wicon.ui.theme.WiconTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WiconTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    // --- DÉBUT DU CODE À COPIER ---

    // Cette fonction s'active à chaque fois qu'une touche est pressée
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        // On vérifie si l'événement vient d'une manette de jeu
        if (event?.source?.and(InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD
            || event?.source?.and(InputDevice.SOURCE_KEYBOARD) == InputDevice.SOURCE_KEYBOARD) {

            // On écrit un message dans la console développeur (Logcat)
            Log.d("TEST_MANETTE", "Bouton appuyé : Code = $keyCode")

            return true // On dit au système "C'est bon, j'ai géré le clic"
        }

        return super.onKeyDown(keyCode, event)
    }

    // --- FIN DU CODE À COPIER ---


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
    WiconTheme {
        Greeting("Android")
    }
}
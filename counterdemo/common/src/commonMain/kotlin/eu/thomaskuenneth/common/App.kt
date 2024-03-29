package eu.thomaskuenneth.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "CounterDemoCompose für ${getPlatformName()}")
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Center
            ) {
                CounterDemo()
            }
        }
    }
}

@Composable
fun CounterDemo() {
    var counter by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier.height(200.dp)
        ) {
            if (counter == 0) {
                Text(
                    text = "Noch nicht geklickt",
                    softWrap = true,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3
                )
            } else {
                Text(
                    text = "$counter",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1
                )
            }
        }
        Button(
            onClick = { counter += 1 }
        ) {
            Text(text = "Klick")
        }
    }
}

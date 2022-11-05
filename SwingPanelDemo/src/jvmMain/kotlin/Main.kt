import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import javax.swing.JComponent

@Composable
@Preview
fun SwingPanelDemoApp() {
    MaterialTheme {
        var height by remember { mutableStateOf(0) }
        var customProperty by remember { mutableStateOf(3) }
        val text by remember(customProperty) { mutableStateOf(SwingDemo.numbers[customProperty - 1]) }
        Column(
            modifier = Modifier.fillMaxSize().padding(all = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SwingPanel<JComponent>(modifier = Modifier.fillMaxWidth().height(height.dp),
                factory = {
                    val slider = SliderWithLabel()
                    slider.addPropertyChangeListener(SliderWithLabel.CUSTOM_PROPERTY) {
                        customProperty = it.newValue as Int
                    }
                    height = slider.preferredSize.height
                    slider
                },
                update = { slider ->
                    with(slider as SliderWithLabel) {
                        setCustomProperty(customProperty)
                    }
                })
            Text(
                text = text,
                modifier = Modifier.padding(top = 16.dp)
            )
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier.weight(1F)
            ) {
                Button(onClick = {
                    customProperty = if (customProperty <= 9)
                        customProperty + 1
                    else
                        1
                }) {
                    Text(text = "+1")
                }
            }
        }
    }
}

fun main() = application(exitProcessOnExit = false) {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SwingPanelDemo",
        state = WindowState(size = DpSize(400.dp, 220.dp))
    ) {
        SwingPanelDemoApp()
    }
    SwingDemo.main(emptyArray())
}

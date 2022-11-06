import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.awt.Dimension
import javax.swing.JComponent

const val MY_CUSTOM_PROPERTY = "myCustomProperty"

fun createComposePanel(): JComponent {
    return ComposePanel().also {
        var oldValue = 0
        it.setContent {
            ComposePanelDemoApp(({ value ->
                val newValue = value.toInt()
                it.firePropertyChange(MY_CUSTOM_PROPERTY, oldValue, newValue)
                oldValue = newValue
            }))
        }
        it.preferredSize = Dimension(300, 96)
    }
}

@Composable
@Preview
fun ComposePanelDemoApp(callback: (Float) -> Unit = {}) {
    var value by remember {
        callback(5F)
        mutableStateOf(5F)
    }
    MaterialTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Slider(
                modifier = Modifier.weight(1F),
                value = value,
                onValueChange = {
                    value = it
                    callback(value)
                },
                valueRange = 1F..10F,
                steps = 10
            )
            Text(
                modifier = Modifier.padding(start = 8.dp).width(32.dp),
                text = value.toInt().toString(),
                textAlign = TextAlign.End
            )
        }
    }
}

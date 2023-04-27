import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JPanel
import kotlin.math.max
import kotlin.math.min

const val CURRENT_VALUE_PROPERTY = "myCustomProperty"

class SliderWithValueWrapper(val initialValue: Int) : JPanel(BorderLayout()) {

    private val currentValueFlow = MutableStateFlow(initialValue.toFloat())

    fun getCurrentValue(): Int = currentValueFlow.value.toInt()

    fun setCurrentValue(value: Int) {
        setCurrentValue(max(1, min(value, 10)), true)
    }

    private fun setCurrentValue(value: Int, updateFlow: Boolean) {
        firePropertyChange(CURRENT_VALUE_PROPERTY, getCurrentValue(), value)
        if (updateFlow) {
            currentValueFlow.value = value.toFloat()
        }
    }

    init {
        val composePanel = ComposePanel()
        composePanel.setContent {
            val currentValueState by currentValueFlow.collectAsState()
            SliderWithValue(currentValueState, ({ newFloat ->
                setCurrentValue(newFloat.toInt(), false)
                currentValueFlow.value = newFloat
            }))
        }
        preferredSize = Dimension(300, 96)
        add(composePanel, BorderLayout.CENTER)
    }
}

@Composable
@Preview
fun SliderWithValue(
    value: Float = 1F, callback: (Float) -> Unit = {}
) {
    MaterialTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            Slider(
                modifier = Modifier.weight(1F),
                value = value,
                onValueChange = callback,
                valueRange = 1F..10F,
                steps = 10
            )
            Text(
                modifier = Modifier.padding(start = 8.dp).width(32.dp),
                text = "${value.toInt()}",
                textAlign = TextAlign.End
            )
        }
    }
}

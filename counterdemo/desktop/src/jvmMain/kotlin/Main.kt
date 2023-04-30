import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import eu.thomaskuenneth.common.MainScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainScreen()
    }
}

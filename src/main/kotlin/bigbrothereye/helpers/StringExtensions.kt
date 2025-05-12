package bigbrothereye.helpers

import java.net.URI
import java.net.URISyntaxException

object StringExtensions {
    fun String.isValidUri(): Boolean {
        return try {
            URI(this).isAbsolute
        } catch (ex: URISyntaxException) {
            false
        }
    }
}
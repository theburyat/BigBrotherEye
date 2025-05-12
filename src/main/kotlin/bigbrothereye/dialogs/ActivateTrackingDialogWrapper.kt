package bigbrothereye.dialogs

import bigbrothereye.constants.Constants
import bigbrothereye.helpers.StringExtensions.isValidUri
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class ActivateTrackingDialogWrapper : DialogWrapper(true) {
    private val serverUrlField = JBTextField()
    private val sessionField = JBTextField()
    private val usernameField = JBTextField()

    init {
        title = ""
        init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row {
                label(Constants.LabelServerUri)
                cell(serverUrlField)
                    .align(AlignX.FILL)
                    .focused()
                    .validationOnApply {
                        if (it.text.isNullOrBlank()) error(Constants.ErrorMustNotBeEmpty)
                        else if (!it.text.isValidUri()) error(Constants.ErrorInvalidUri)
                        else null
                    }
            }
            row {
                label(Constants.LabelSessionId)
                cell(sessionField)
                    .align(AlignX.FILL)
                    .validationOnApply {
                        if (it.text.toIntOrNull() == null) error(Constants.ErrorInvalidFormat) else null
                    }
            }
            row {
                label(Constants.LabelUsername)
                cell(usernameField)
                    .align(AlignX.FILL)
                    .validationOnApply {
                        if (it.text.isNullOrBlank()) error(Constants.ErrorMustNotBeEmpty) else null
                    }
            }
        }
    }

    fun getServerUrl(): String = serverUrlField.text
    fun getSession(): Int = sessionField.text.toInt()
    fun getUsername(): String = usernameField.text
}
